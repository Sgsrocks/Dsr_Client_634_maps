// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.*;
import java.io.*;
import java.util.*;
import java.awt.*;

public final class Flo {

	public static String loc = signlink.findcachedir();
	public static void unpackConfig(StreamLoader streamLoader) {
		Stream stream = new Stream(streamLoader.getDataForName("flo.dat"));
		int cacheSize = stream.readUnsignedWord();
		if(cache == null)
			cache = new Flo[cacheSize];
		for(int j = 0; j < cacheSize; j++) {
			try {
				if(cache[j] == null)
					cache[j] = new Flo();
				cache[j].readValues(stream);
				//System.out.println("Floor id: "+j);
			} catch (Exception e) {
				e.printStackTrace();
			}
        } 
		System.out.println("Loaded Underlay Floors: "+cacheSize);
	}
	
	private void readValues(Stream stream) {
		do {
			int opcode = stream.readUnsignedByte();
			switch(opcode) {
				case 1:
					anInt390 = stream.read3Bytes();
					method262(anInt390, true);
					break;
				case 2:
					anInt391 = stream.readUnsignedByte();
					break;
				case 3:
					break;
				case 5:
					aBoolean393 = false;
					break;
				case 6:
					stream.readString();
					break;
				case 7:
					int j = anInt394;
					int k = anInt395;
					int l = anInt396;
					int i1 = anInt397;
					int j1 = stream.read3Bytes();
					anInt399 = j1;
					method262(j1, true);
					anInt394 = j;
					anInt395 = k;
					anInt396 = l;
					anInt397 = i1;
					anInt398 = i1;
					break;
                case 0:
                    return;
				default:
					System.out.println("Error unrecognised config code: " + opcode);
					break;
			}
		} while(true);
	}

	private void method262(int color, boolean flag) {
		double d = (double)(color >> 16 & 0xff) / 256D;
		double d1 = (double)(color >> 8 & 0xff) / 256D;
		double d2 = (double)(color & 0xff) / 256D;
		double d3 = d;
		if(d1 < d3)
			d3 = d1;
		if(d2 < d3)
			d3 = d2;
		double d4 = d;
		if(d1 > d4)
			d4 = d1;
		if(d2 > d4)
			d4 = d2;
		double d5 = 0.0D;
		double d6 = 0.0D;
		double d7 = (d3 + d4) / 2D;
		if(d3 != d4)
		{
			if(d7 < 0.5D)
				d6 = (d4 - d3) / (d4 + d3);
			if(d7 >= 0.5D)
				d6 = (d4 - d3) / (2D - d4 - d3);
			if(d == d4)
				d5 = (d1 - d2) / (d4 - d3);
			else
			if(d1 == d4)
				d5 = 2D + (d2 - d) / (d4 - d3);
			else
			if(d2 == d4)
				d5 = 4D + (d - d1) / (d4 - d3);
		}
		d5 /= 6D;
		anInt394 = (int)(d5 * 256D);
		anInt395 = (int)(d6 * 256D);
		anInt396 = (int)(d7 * 256D);
		if(anInt395 < 0)
			anInt395 = 0;
		else
		if(anInt395 > 255)
			anInt395 = 255;
		if(anInt396 < 0)
			anInt396 = 0;
		else
		if(anInt396 > 255)
			anInt396 = 255;
		if(d7 > 0.5D)
			anInt398 = (int)((1.0D - d7) * d6 * 512D);
		else
			anInt398 = (int)(d7 * d6 * 512D);
		if(anInt398 < 1)
			anInt398 = 1;
		anInt397 = (int)(d5 * (double)anInt398);
		int hOffset = anInt394;
		int sOffset = anInt395;
		int lOffset = anInt396;
		anInt399 = method263(hOffset, sOffset, lOffset);
	}

	private int method263(int i, int j, int k) {
		if(k > 179)
			j /= 2;
		if(k > 192)
			j /= 2;
		if(k > 217)
			j /= 2;
		if(k > 243)
			j /= 2;
		return (i / 4 << 10) + (j / 32 << 7) + k / 2;
	}

	private Flo() {
		anInt391 = -1;
		aBoolean393 = true;
	}
	
	public int anInt3063;
	public static Flo cache[];
	public int anInt390;//groundColor
	public int anInt391;//groundTexture
	public boolean aBoolean393;//occlude
	public int anInt394;//hue
	public int anInt395;//saturation
	public int anInt396;//lightness
	public int anInt397;//hue2
	public int anInt398;//pCDivider
	public int anInt399;//hslColor

}
