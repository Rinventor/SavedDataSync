package com.rinventor.transportmod.network;

import com.rinventor.transportmod.TransportMod;
import com.rinventor.transportmod.core.base.PTMDbg;
import com.rinventor.transportmod.core.base.PTMEntity;
import com.rinventor.transportmod.core.data.PTMStaticData;
import com.rinventor.transportmod.objects.StationStop;
import com.rinventor.transportmod.objects.TransportLine;
import com.rinventor.transportmod.objects.TransportTracker;
import com.rinventor.transportmod.objects.blockentities.information_screen.InformationScreenBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClientAccess {

    public static boolean setNumberNBT(String name, double valueIn) {
        Player entity = Minecraft.getInstance().player;
        PTMEntity.setNumberNBT(name, valueIn, entity);
        return true;
    }

    public static boolean setTextNBT(String name, String valueIn) {
        Player entity = Minecraft.getInstance().player;
        PTMEntity.setTextNBT(name, valueIn, entity);
        return true;
    }

    public static boolean syncInformationScreen(BlockPos pos, String data, boolean isOn, boolean wall, boolean left, boolean b, boolean tb, boolean t, boolean u, boolean o, boolean s) {
        Level level = Minecraft.getInstance().level;
        if (level.isLoaded(pos)) {
            if (level.getBlockEntity(pos) instanceof InformationScreenBlockEntity be) {
                be.data = data;
                be.isOn = isOn;
                be.wall = wall;
                be.left = left;
                be.b = b;
                be.tb = tb;
                be.t = t;
                be.u = u;
                be.o = o;
                be.s = s;
                be.setChanged();
                return true;
            }
        }
        return false;
    }

    public static boolean announcementSound(BlockPos pos, String filename) {
        Level level = Minecraft.getInstance().level;
        if (level.isLoaded(pos)) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            level.playLocalSound(x, y, z, new SoundEvent(new ResourceLocation(TransportMod.MOD_ID + ":" + filename)), SoundSource.AMBIENT, 1, 1, false);
            return true;
        }
        return false;
    }

    public static boolean entityYawSync(BlockPos pos, String entity, double yaw) {
        Level level = Minecraft.getInstance().level;
        if (level.isLoaded(pos)) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            final Vec3 _center = new Vec3(x, y, z);
            List<Entity> _entfound = level.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
            for (Entity ent : _entfound) {
                if (entity.equals(ent.toString().substring(0, ent.toString().indexOf(",")))) {
                    ent.setYRot((float) yaw);
                    ent.setYBodyRot((float) yaw);
                    ent.setYHeadRot((float) yaw);
                    ent.yRotO = (float) yaw;
                    ent.xRotO = (float) yaw;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dataSync1(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10, int a11, int a12, int a13, int a14, int a15, int a16) {
        PTMStaticData.car_w = a1;
        PTMStaticData.car_rh = a2;
        PTMStaticData.car_s = a3;
        PTMStaticData.car_n = a4;
        PTMStaticData.policecar_w = a5;
        PTMStaticData.policecar_rh = a6;
        PTMStaticData.policecar_s = a7;
        PTMStaticData.policecar_n = a8;
        PTMStaticData.ambulance_w = a9;
        PTMStaticData.ambulance_rh = a10;
        PTMStaticData.ambulance_s = a11;
        PTMStaticData.ambulance_n = a12;
        PTMStaticData.firetruck_w = a13;
        PTMStaticData.firetruck_rh = a14;
        PTMStaticData.firetruck_s = a15;
        PTMStaticData.firetruck_n = a16;
        return true;
    }

    public static boolean dataSync2(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10, int a11, int a12, int a13, int a14, int a15, int a16) {
        PTMStaticData.prc_sand = a1;
        PTMStaticData.prc_wood = a2;
        PTMStaticData.prc_stone = a3;
        PTMStaticData.prc_coal = a4;
        PTMStaticData.prc_iron = a5;
        PTMStaticData.prc_gold = a6;
        PTMStaticData.prc_lapis = a7;
        PTMStaticData.prc_redstone = a8;
        PTMStaticData.prc_emerald = a9;
        PTMStaticData.prc_diamond = a10;
        PTMStaticData.prc_scooter = a11;
        PTMStaticData.prc_motorbike = a12;
        PTMStaticData.prc_car = a13;
        PTMStaticData.prc_police_emcar = a14;
        PTMStaticData.prc_ambulance = a15;
        PTMStaticData.prc_firetruck = a16;
        return true;
    }

    public static boolean dataSync3(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10, int a11, int a12, int a13, int a14, int a15, int a16) {
        PTMStaticData.prc_bus = a1;
        PTMStaticData.prc_long_bus = a2;
        PTMStaticData.prc_old_trolleybus = a3;
        PTMStaticData.prc_new_trolleybus = a4;
        PTMStaticData.prc_long_trolleybus = a5;
        PTMStaticData.prc_old_tram = a6;
        PTMStaticData.prc_modern_tram = a7;
        PTMStaticData.prc_train_a = a8;
        PTMStaticData.prc_train_b = a9;
        PTMStaticData.prc_train_c = a10;
        PTMStaticData.prc_train_d = a11;
        PTMStaticData.prc_train_e = a12;
        PTMStaticData.prc_skyway = a13;
        PTMStaticData.prc_10F = a14;
        PTMStaticData.prc_100F = a15;
        PTMStaticData.start_fuel = a16;
        return true;
    }

    public static boolean dataSync4(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int b1, int b2, int b3, int b4, int a10, int a11, int a12, double a13, double a14, double a15) {
        PTMStaticData.pedestrian_w = a1;
        PTMStaticData.pedestrian_rh = a2;
        PTMStaticData.pedestrian_s = a3;
        PTMStaticData.pedestrian_n = a4;
        PTMStaticData.city_driving_speed = a5;
        PTMStaticData.world_driving_speed = a6;
        PTMStaticData.prc_ticket = a7;
        PTMStaticData.prc_card = a8;
        PTMStaticData.prc_ticket_10 = a9;
        PTMStaticData.prc_ticket_24h = b1;
        PTMStaticData.prc_ticket_72h = b2;
        PTMStaticData.prc_ticket_week = b3;
        PTMStaticData.prc_ticket_month = b4;
        PTMStaticData.prc_taxi_starting = a10;
        PTMStaticData.prc_taxi_50B = a11;
        PTMStaticData.prc_scooter_20B = a12;
        PTMStaticData.latitude = a13;
        PTMStaticData.longitude = a14;
        PTMStaticData.escalator_speed = a15;
        return true;
    }

    public static boolean dataSync5(boolean a1, boolean a2, boolean a3, boolean a4, boolean a5, boolean a6, boolean a7, boolean a8, boolean a9, boolean a10, boolean a11, boolean a12, boolean a13, boolean a14, boolean a15, boolean a16, boolean a17) {
        PTMStaticData.metric_units = a1;
        PTMStaticData.tram_old = a2;
        PTMStaticData.underground_old = a3;
        PTMStaticData.underground_scr = a4;
        PTMStaticData.overground_old = a5;
        PTMStaticData.overground_electric = a6;
        PTMStaticData.overground_scr = a7;
        PTMStaticData.underground_escalators = a8;
        PTMStaticData.real_daylight = a9;
        PTMStaticData.remove_extra_entities = a10;
        PTMStaticData.remove_thown_items = a11;
        PTMStaticData.city_ambient_sounds = a12;
        PTMStaticData.clear_vehicles_on_world_load = a13;
        PTMStaticData.transport_announcements = a14;
        PTMStaticData.spawn_pedestrians = a15;
        PTMStaticData.spawn_traffic = a16;
        PTMStaticData.spawn_transport = a17;
        return true;
    }

    public static boolean dataSync6(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, boolean a9) {
        PTMStaticData.snd_city = a1;
        PTMStaticData.snd_port = a2;
        PTMStaticData.snd_airport = a3;
        PTMStaticData.snd_depot = a4;
        PTMStaticData.snd_underground = a5;
        PTMStaticData.depot = a6;
        PTMStaticData.pdt_walkable = a7;
        PTMStaticData.time_format = a8;
        PTMStaticData.transport_lights = a9;
        return true;
    }

    public static boolean dataSync7(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15, String a16) {
        List<StationStop> stopsIn = new ArrayList<StationStop>();
        List<String> list = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16);
        for (String arg : list) {
            for (String str : arg.split("&")) {
                stopsIn.add(StationStop.format(str));
            }
        }
        PTMStaticData.stops = stopsIn;
        return true;
    }

    public static boolean dataSync8(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15, String a16) {
        List<TransportLine> linesIn = new ArrayList<>();
        List<String> list = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16);
        for (String arg : list) {
            for (String str : arg.split("&")) {
                if (str.length() >= 2)
                    linesIn.add(TransportLine.format(str));
            }
        }
        PTMStaticData.transport_lines = linesIn;
        return true;
    }

    public static boolean dataSync9(String a1, String a2, String a3, String a4, String a5, String a6, String a7, String a8, String a9, String a10, String a11, String a12, String a13, String a14, String a15, String a16) {
        List<TransportTracker> trackersIn = new ArrayList<TransportTracker>();
        if (a1.trim().length() < 2)
            return false;

        List<String> list = List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16);
        for (String arg : list) {
            for (String str : arg.split("&")) {
                trackersIn.add(TransportTracker.format(str));
            }
        }
        PTMStaticData.transport_trackers = trackersIn;
        return true;
    }

}
