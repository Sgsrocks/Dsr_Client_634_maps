// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.io.*;
import sign.signlink;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public final class ObjectDef
{
	public static ObjectDef forID(int i) {
		if (i > streamIndices.length)
			i = streamIndices.length - 1;
		for(int j = 0; j < 20; j++)
			if(cache[j].type == i) {
				return cache[j];
			}
			cacheIndex = (cacheIndex + 1) % 20;
			ObjectDef objectDef = cache[cacheIndex];
			try {
				buffer.currentOffset = streamIndices[i];
			} catch(Exception e) {
				e.printStackTrace();
			}
			objectDef.type = i;
			objectDef.setDefaults();
			objectDef.readValues(buffer);
			switch(i) {
				case 23735: 
					objectDef.anInt781 = -1; 
				break;
				case 31299: 
					objectDef.anInt781 = -1; 
				break;
			}
		return objectDef;
	}
	
    private void setDefaults()
    {
        anIntArray773 = null;
        anIntArray776 = null;
        name = null;
        description = null;
        modifiedModelColors = null;
        originalModelColors = null;
        anInt744 = 1;
        anInt761 = 1;
        aBoolean767 = true;
        aBoolean757 = true;
        hasActions = false;
        aBoolean762 = false;
        aBoolean769 = false;
        aBoolean764 = false;
        anInt781 = -1;
        anInt775 = 16;
        aByte737 = 0;
        aByte742 = 0;
        actions = null;
        anInt746 = -1;
        anInt758 = -1;
        aBoolean751 = false;
        aBoolean779 = true;
        anInt748 = 128;
        anInt772 = 128;
        anInt740 = 128;
        anInt768 = 0;
        anInt738 = 0;
        anInt745 = 0;
        anInt783 = 0;
        aBoolean736 = false;
        aBoolean766 = false;
        anInt760 = -1;
        anInt774 = -1;
        anInt749 = -1;
        childrenIDs = null;
    }

    public void method574(OnDemandFetcher class42_sub1)
    {
        if(anIntArray773 == null)
            return;
        for(int j = 0; j < anIntArray773.length; j++)
            class42_sub1.method560(anIntArray773[j] & 0xffff, 0);
    }

    public static void nullLoader()
    {
        mruNodes1 = null;
        mruNodes2 = null;
        streamIndices = null;
        cache = null;
	buffer = null;
    }

    public static void unpackConfig(StreamLoader streamLoader)
    {
        buffer = new Stream(streamLoader.getDataForName("loc.dat"));
        Stream buffer = new Stream(streamLoader.getDataForName("loc.idx"));
        int totalObjects = buffer.readUnsignedWord();
	System.out.println("667 Object Amount: " + totalObjects);
        streamIndices = new int[totalObjects + 40000];
        int i = 2;
        for(int j = 0; j < totalObjects; j++)
        {
            streamIndices[j] = i;
            i += buffer.readUnsignedWord();
        }
        cache = new ObjectDef[20];
        for(int k = 0; k < 20; k++)
            cache[k] = new ObjectDef();
    }

    public boolean method577(int i) {
        if(anIntArray776 == null) {
            if(anIntArray773 == null)
                return true;
            if(i != 10)
                return true;
            boolean flag1 = true;
            for(int k = 0; k < anIntArray773.length; k++)
                flag1 &= Model.method463(anIntArray773[k] & 0xffff);
            return flag1;
        }
        for(int j = 0; j < anIntArray776.length; j++)
            if(anIntArray776[j] == i)
                return Model.method463(anIntArray773[j] & 0xffff);

        return true;
    }

    public Model method578(int i, int j, int k, int l, int i1, int j1, int k1) {
        Model model = method581(i, k1, j);
        if(model == null)
            return null;
        if(aBoolean762 || aBoolean769)
            model = new Model(aBoolean762, aBoolean769, model);
        if(aBoolean762) {
            int l1 = (k + l + i1 + j1) / 4;
            for(int i2 = 0; i2 < model.anInt1626; i2++) {
                int j2 = model.anIntArray1627[i2];
                int k2 = model.anIntArray1629[i2];
                int l2 = k + ((l - k) * (j2 + 64)) / 128;
                int i3 = j1 + ((i1 - j1) * (j2 + 64)) / 128;
                int j3 = l2 + ((i3 - l2) * (k2 + 64)) / 128;
                model.anIntArray1628[i2] += j3 - l1;
            }
            model.method467();
        }
        return model;
    }

    public boolean method579()
    {
        if(anIntArray773 == null)
            return true;
        boolean flag1 = true;
        for(int i = 0; i < anIntArray773.length; i++)
            flag1 &= Model.method463(anIntArray773[i] & 0xffff);
            return flag1;
    }

    public ObjectDef method580()
    {
        int i = -1;
        if(anInt774 != -1)
        {
            VarBit varBit = VarBit.cache[anInt774];
            int j = varBit.anInt648;
            int k = varBit.anInt649;
            int l = varBit.anInt650;
            int i1 = client.anIntArray1232[l - k];
            i = clientInstance.variousSettings[j] >> k & i1;
        } else
        if(anInt749 != -1)
            i = clientInstance.variousSettings[anInt749];
        if(i < 0 || i >= childrenIDs.length || childrenIDs[i] == -1)
            return null;
        else
            return forID(childrenIDs[i]);
    }

    private Model method581(int j, int k, int l) {
        Model model = null;
        long l1;
        if(anIntArray776 == null) {
            if(j != 10)
                return null;
            l1 = (long)((type << 6) + l) + ((long)(k + 1) << 32);
            Model model_1 = (Model) mruNodes2.insertFromCache(l1);
            if(model_1 != null)
                return model_1;
            if(anIntArray773 == null)
                return null;
            boolean flag1 = aBoolean751 ^ (l > 3);
            int k1 = anIntArray773.length;
            for(int i2 = 0; i2 < k1; i2++) {
                int l2 = anIntArray773[i2];
                if(flag1)
                    l2 += 0x10000;
                model = (Model) mruNodes1.insertFromCache(l2);
                if(model == null) {
                    model = Model.method462(l2 & 0xffff);
                    if(model == null)
                        return null;
                    if(flag1)
                        model.method477();
                    mruNodes1.removeFromCache(model, l2);
                }
                if(k1 > 1)
                    aModelArray741s[i2] = model;
            }
            if(k1 > 1)
                model = new Model(k1, aModelArray741s);
        } else {
            int i1 = -1;
            for(int j1 = 0; j1 < anIntArray776.length; j1++) {
                if(anIntArray776[j1] != j)
                    continue;
                i1 = j1;
                break;
            }
            if(i1 == -1)
                return null;
            l1 = (long)((type << 8) + (i1 << 3) + l) + ((long)(k + 1) << 32);
            Model model_2 = (Model) mruNodes2.insertFromCache(l1);
            if(model_2 != null)
                return model_2;
            int j2 = anIntArray773[i1];
            boolean flag3 = aBoolean751 ^ (l > 3);
            if(flag3)
                j2 += 0x10000;
            model = (Model) mruNodes1.insertFromCache(j2);
            if(model == null) {
                model = Model.method462(j2 & 0xffff);
                if(model == null)
                    return null;
                if(flag3)
                    model.method477();
                mruNodes1.removeFromCache(model, j2);
            }
        }
        boolean flag;
        flag = anInt748 != 128 || anInt772 != 128 || anInt740 != 128;
        boolean flag2;
        flag2 = anInt738 != 0 || anInt745 != 0 || anInt783 != 0;
        Model model_3 = new Model(modifiedModelColors == null, FrameReader.method532(k), l == 0 && k == -1 && !flag && !flag2, model);
        if(k != -1) {
            model_3.method469();
            model_3.method470(k);
            model_3.anIntArrayArray1658 = null;
            model_3.anIntArrayArray1657 = null;
        }
        while(l-- > 0) 
            model_3.method473();
        if(modifiedModelColors != null) {
            for(int k2 = 0; k2 < modifiedModelColors.length; k2++)
                model_3.method476(modifiedModelColors[k2], originalModelColors[k2]);
        }
        if(flag)
            model_3.method478(anInt748, anInt740, anInt772);
        if(flag2)
            model_3.method475(anInt738, anInt745, anInt783);
        model_3.method479(64, 9000, -90, -580, -90, !aBoolean769);
		if(anInt760 == 1)
            model_3.anInt1654 = model_3.modelHeight;
        mruNodes2.removeFromCache(model_3, l1);
        return model_3;
    }

    private void readValues(Stream buffer) {
		int i = -1;
		label0: do {
			int opcode;
			do {
				opcode = buffer.readUnsignedByte();
				if (opcode == 0)
					break label0;
				if (opcode == 1) {
					int k = buffer.readUnsignedByte();
					if (k > 0)
						if (anIntArray773 == null || lowMem) {
							anIntArray776 = new int[k];
							anIntArray773 = new int[k];
							for (int k1 = 0; k1 < k; k1++) {
								anIntArray773[k1] = buffer.readUnsignedWord();
								anIntArray776[k1] = buffer.readUnsignedByte();
							}
						} else {
							buffer.currentOffset += k * 3;
						}
				} else if (opcode == 2)
					name = buffer.readString();
				else if (opcode == 3)
					description = buffer.readBytes();
				else if (opcode == 5) {
					int l = buffer.readUnsignedByte();
					if (l > 0)
						if (anIntArray773 == null || lowMem) {
							anIntArray776 = null;
							anIntArray773 = new int[l];
							for (int l1 = 0; l1 < l; l1++)
								anIntArray773[l1] = buffer.readUnsignedWord();
						} else {
							;//buffer.currentOffset += l * 2;
						}
				} else if (opcode == 14)
					anInt744 = buffer.readUnsignedByte();
				else if (opcode == 15)
					anInt761 = buffer.readUnsignedByte();
				else if (opcode == 17)
					aBoolean767 = false;
				else if (opcode == 18)
					aBoolean757 = false;
				else if (opcode == 19) {
					i = buffer.readUnsignedByte();
					if (i == 1 && name != null)
						hasActions = true;
				} else if (opcode == 21)
					aBoolean762 = true;
				else if (opcode == 22)
					aBoolean769 = false;//
				else if (opcode == 23)
					aBoolean764 = true;
				else if (opcode == 24) {
					anInt781 = buffer.readUnsignedWord();
					if (anInt781 == 65535)
						anInt781 = -1;
				} else if (opcode == 28)
					anInt775 = buffer.readUnsignedByte();
				else if (opcode == 29)
					aByte737 = buffer.readSignedByte();
				else if (opcode == 39)
					aByte742 = buffer.readSignedByte();
				else if (opcode >= 30 && opcode < 39) {
					if (actions == null)
						actions = new String[10];
					actions[opcode - 30] = buffer.readString();
					if (actions[opcode - 30].equalsIgnoreCase("hidden"))
						actions[opcode - 30] = null;
				} else if (opcode == 40) {
					int i1 = buffer.readUnsignedByte();
					modifiedModelColors = new int[i1];
					originalModelColors = new int[i1];
					for (int i2 = 0; i2 < i1; i2++) {
						modifiedModelColors[i2] = buffer.readUnsignedWord();
						originalModelColors[i2] = buffer.readUnsignedWord();
					}
				} else if (opcode == 60)
					anInt746 = buffer.readUnsignedWord();
				else if (opcode == 62)
					aBoolean751 = true;
				else if (opcode == 64)
					aBoolean779 = false;
				else if (opcode == 65)
					anInt748 = buffer.readUnsignedWord();
				else if (opcode == 66)
					anInt772 = buffer.readUnsignedWord();
				else if (opcode == 67)
					anInt740 = buffer.readUnsignedWord();
				else if (opcode == 68)
					anInt758 = buffer.readUnsignedWord();
				else if (opcode == 69)
					anInt768 = buffer.readUnsignedByte();
				else if (opcode == 70)
					anInt738 = buffer.readSignedWord();
				else if (opcode == 71)
					anInt745 = buffer.readSignedWord();
				else if (opcode == 72)
					anInt783 = buffer.readSignedWord();
				else if (opcode == 73)
					aBoolean736 = true;
				else if (opcode == 74) {
					aBoolean766 = true;
				} else {
					if (opcode != 75)
						continue;
					anInt760 = buffer.readUnsignedByte();
				}
				continue label0;
			} while (opcode != 77);
				anInt774 = buffer.readUnsignedWord();
			if (anInt774 == 65535)
				anInt774 = -1;
				anInt749 = buffer.readUnsignedWord();
			if (anInt749 == 65535)
				anInt749 = -1;
			int j1 = buffer.readUnsignedByte();
			childrenIDs = new int[j1 + 1];
			for (int j2 = 0; j2 <= j1; j2++) {
				childrenIDs[j2] = buffer.readUnsignedWord();
				if (childrenIDs[j2] == 65535)
					childrenIDs[j2] = -1;
			}

		} while (true);
		if (i == -1) {
			hasActions = anIntArray773 != null && (anIntArray776 == null || anIntArray776[0] == 10);
			if (actions != null)
				hasActions = true;
		}
		if (aBoolean766) {
			aBoolean767 = false;
			aBoolean757 = false;
		}
		if (anInt760 == -1)
			anInt760 = aBoolean767 ? 1 : 0;
    }

    private ObjectDef()
    {
        type = -1;
    }

    private static Stream buffer;
    public boolean aBoolean736;
    private byte aByte737;
    private int anInt738;
    public String name;
    private int anInt740;
    private static final Model[] aModelArray741s = new Model[4];
    private byte aByte742;
    public int anInt744;
    private int anInt745;
    public int anInt746;
    private int[] originalModelColors;
    private int anInt748;
    public int anInt749;
    private boolean aBoolean751;
    public static boolean lowMem;
    private static Stream stream;
    public int type;
    private static int[] streamIndices;
    public boolean aBoolean757;
    public int anInt758;
    public int childrenIDs[];
    private int anInt760;
    public int anInt761;
    public boolean aBoolean762;
    public boolean aBoolean764;
    public static client clientInstance;
    private boolean aBoolean766;
    public boolean aBoolean767;
    public int anInt768;
    private boolean aBoolean769;
    private static int cacheIndex;
    private int anInt772;
    private int[] anIntArray773;
    public int anInt774;
    public int anInt775;
    private int[] anIntArray776;
    public byte description[];
    public boolean hasActions;
    public boolean aBoolean779;
    public static MRUNodes mruNodes2 = new MRUNodes(30);
    public int anInt781;
    private static ObjectDef[] cache;
    private int anInt783;
    private int[] modifiedModelColors;
    public static MRUNodes mruNodes1 = new MRUNodes(500);
    public String actions[];

}
