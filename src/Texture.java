// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

final class Texture extends DrawingArea {

	private static boolean smoothShading = true;

	public static void nullLoader()
	{
		anIntArray1468 = null;
		anIntArray1468 = null;
		anIntArray1470 = null;
		anIntArray1471 = null;
		anIntArray1472 = null;
		aBackgroundArray1474s = null;
		aBooleanArray1475 = null;
		anIntArray1476 = null;
		anIntArrayArray1478 = null;
		anIntArrayArray1479 = null;
		anIntArray1480 = null;
		anIntArray1482 = null;
		anIntArrayArray1483 = null;
	}

	public static void method364()
	{
		anIntArray1472 = new int[DrawingArea.height];
		for(int j = 0; j < DrawingArea.height; j++)
			anIntArray1472[j] = DrawingArea.width * j;

		textureInt1 = DrawingArea.width / 2;
		textureInt2 = DrawingArea.height / 2;
	}

	public static void method365(int j, int k)
	{
	   anIntArray1472 = new int[k];
		for(int l = 0; l < k; l++)
			anIntArray1472[l] = j * l;

		textureInt1 = j / 2;
		textureInt2 = k / 2;
	}

	public static void method366()
	{
		anIntArrayArray1478 = null;
		for(int j = 0; j < 50; j++)
			anIntArrayArray1479[j] = null;

	}

	public static void method367()
	{
		if(anIntArrayArray1478 == null)
		{
			anInt1477 = 20;//was parameter
			if(lowMem)
				anIntArrayArray1478 = new int[anInt1477][16384];
			else
				anIntArrayArray1478 = new int[anInt1477][0x10000];
			for(int k = 0; k < 50; k++)
				anIntArrayArray1479[k] = null;

		}
	}

	public static void method368(StreamLoader streamLoader)
	{
		anInt1473 = 0;
		for(int j = 0; j < 50; j++)
			try
			{
				aBackgroundArray1474s[j] = new Background(streamLoader, String.valueOf(j), 0);
				if(lowMem && aBackgroundArray1474s[j].anInt1456 == 128)
					aBackgroundArray1474s[j].method356();
				else
					aBackgroundArray1474s[j].method357();
				anInt1473++;
			}
			catch(Exception _ex) { }

	}

	public static int method369(int i)
	{
		if(anIntArray1476[i] != 0)
			return anIntArray1476[i];
		int k = 0;
		int l = 0;
		int i1 = 0;
		int j1 = anIntArrayArray1483[i].length;
		for(int k1 = 0; k1 < j1; k1++)
		{
			k += anIntArrayArray1483[i][k1] >> 16 & 0xff;
			l += anIntArrayArray1483[i][k1] >> 8 & 0xff;
			i1 += anIntArrayArray1483[i][k1] & 0xff;
		}

		int l1 = (k / j1 << 16) + (l / j1 << 8) + i1 / j1;
		l1 = method373(l1, 1.3999999999999999D);
		if(l1 == 0)
			l1 = 1;
		anIntArray1476[i] = l1;
		return l1;
	}

	public static void method370(int i)
	{
		if(anIntArrayArray1479[i] == null)
			return;
		anIntArrayArray1478[anInt1477++] = anIntArrayArray1479[i];
		anIntArrayArray1479[i] = null;
	}

	private static int[] method371(int i)
	{
		if (i == 1)
        {
            i = 24;
        }
		anIntArray1480[i] = anInt1481++;
		if(anIntArrayArray1479[i] != null)
			return anIntArrayArray1479[i];
		int ai[];
		if(anInt1477 > 0)
		{
			ai = anIntArrayArray1478[--anInt1477];
			anIntArrayArray1478[anInt1477] = null;
		} else
		{
			int j = 0;
			int k = -1;
			for(int l = 0; l < anInt1473; l++)
				if(anIntArrayArray1479[l] != null && (anIntArray1480[l] < j || k == -1))
				{
					j = anIntArray1480[l];
					k = l;
				}

			ai = anIntArrayArray1479[k];
			anIntArrayArray1479[k] = null;
		}
		anIntArrayArray1479[i] = ai;
		Background background = aBackgroundArray1474s[i];
		int ai1[] = anIntArrayArray1483[i];
		if(lowMem)
		{
			aBooleanArray1475[i] = false;
			for(int i1 = 0; i1 < 4096; i1++)
			{
				int i2 = ai[i1] = ai1[background.aByteArray1450[i1]] & 0xf8f8ff;
				if(i2 == 0)
					aBooleanArray1475[i] = true;
				ai[4096 + i1] = i2 - (i2 >>> 3) & 0xf8f8ff;
				ai[8192 + i1] = i2 - (i2 >>> 2) & 0xf8f8ff;
				ai[12288 + i1] = i2 - (i2 >>> 2) - (i2 >>> 3) & 0xf8f8ff;
			}

		} else
		{
			if(background.anInt1452 == 64)
			{
				for(int j1 = 0; j1 < 128; j1++)
				{
					for(int j2 = 0; j2 < 128; j2++)
						ai[j2 + (j1 << 7)] = ai1[background.aByteArray1450[(j2 >> 1) + ((j1 >> 1) << 6)]];

				}

			} else
			{
				for(int k1 = 0; k1 < 16384; k1++)
					ai[k1] = ai1[background.aByteArray1450[k1]];

			}
			aBooleanArray1475[i] = false;
			for(int l1 = 0; l1 < 16384; l1++)
			{
				ai[l1] &= 0xf8f8ff;
				int k2 = ai[l1];
				if(k2 == 0)
					aBooleanArray1475[i] = true;
				ai[16384 + l1] = k2 - (k2 >>> 3) & 0xf8f8ff;
				ai[32768 + l1] = k2 - (k2 >>> 2) & 0xf8f8ff;
				ai[49152 + l1] = k2 - (k2 >>> 2) - (k2 >>> 3) & 0xf8f8ff;
			}

		}
		return ai;
	}

	public static void method372(double d)
	{
		d += Math.random() * 0.029999999999999999D - 0.014999999999999999D;
		int j = 0;
		for(int k = 0; k < 512; k++)
		{
			double d1 = (double)(k / 8) / 64D + 0.0078125D;
			double d2 = (double)(k & 7) / 8D + 0.0625D;
			for(int k1 = 0; k1 < 128; k1++)
			{
				double d3 = (double)k1 / 128D;
				double d4 = d3;
				double d5 = d3;
				double d6 = d3;
				if(d2 != 0.0D)
				{
					double d7;
					if(d3 < 0.5D)
						d7 = d3 * (1.0D + d2);
					else
						d7 = (d3 + d2) - d3 * d2;
					double d8 = 2D * d3 - d7;
					double d9 = d1 + 0.33333333333333331D;
					if(d9 > 1.0D)
						d9--;
					double d10 = d1;
					double d11 = d1 - 0.33333333333333331D;
					if(d11 < 0.0D)
						d11++;
					if(6D * d9 < 1.0D)
						d4 = d8 + (d7 - d8) * 6D * d9;
					else
					if(2D * d9 < 1.0D)
						d4 = d7;
					else
					if(3D * d9 < 2D)
						d4 = d8 + (d7 - d8) * (0.66666666666666663D - d9) * 6D;
					else
						d4 = d8;
					if(6D * d10 < 1.0D)
						d5 = d8 + (d7 - d8) * 6D * d10;
					else
					if(2D * d10 < 1.0D)
						d5 = d7;
					else
					if(3D * d10 < 2D)
						d5 = d8 + (d7 - d8) * (0.66666666666666663D - d10) * 6D;
					else
						d5 = d8;
					if(6D * d11 < 1.0D)
						d6 = d8 + (d7 - d8) * 6D * d11;
					else
					if(2D * d11 < 1.0D)
						d6 = d7;
					else
					if(3D * d11 < 2D)
						d6 = d8 + (d7 - d8) * (0.66666666666666663D - d11) * 6D;
					else
						d6 = d8;
				}
				int l1 = (int)(d4 * 256D);
				int i2 = (int)(d5 * 256D);
				int j2 = (int)(d6 * 256D);
				int k2 = (l1 << 16) + (i2 << 8) + j2;
				k2 = method373(k2, d);
				if(k2 == 0)
					k2 = 1;
				anIntArray1482[j++] = k2;
			}

		}

		for(int l = 0; l < 50; l++)
			if(aBackgroundArray1474s[l] != null)
			{
				int ai[] = aBackgroundArray1474s[l].anIntArray1451;
				anIntArrayArray1483[l] = new int[ai.length];
				for(int j1 = 0; j1 < ai.length; j1++)
				{
					anIntArrayArray1483[l][j1] = method373(ai[j1], d);
					if((anIntArrayArray1483[l][j1] & 0xf8f8ff) == 0 && j1 != 0)
						anIntArrayArray1483[l][j1] = 1;
				}

			}

		for(int i1 = 0; i1 < 50; i1++)
			method370(i1);

	}

	private static int method373(int i, double d)
	{
		double d1 = (double)(i >> 16) / 256D;
		double d2 = (double)(i >> 8 & 0xff) / 256D;
		double d3 = (double)(i & 0xff) / 256D;
		d1 = Math.pow(d1, d);
		d2 = Math.pow(d2, d);
		d3 = Math.pow(d3, d);
		int j = (int)(d1 * 256D);
		int k = (int)(d2 * 256D);
		int l = (int)(d3 * 256D);
		return (j << 16) + (k << 8) + l;
	}

	public static void method374(int i, int j, int k, int l, int i1, int j1, int k1, int l1, 
			int i2)
	{
		int j2 = 0;
		int k2 = 0;
		if(j != i)
		{
			j2 = (i1 - l << 16) / (j - i);
			k2 = (l1 - k1 << 15) / (j - i);
		}
		int l2 = 0;
		int i3 = 0;
		if(k != j)
		{
			l2 = (j1 - i1 << 16) / (k - j);
			i3 = (i2 - l1 << 15) / (k - j);
		}
		int j3 = 0;
		int k3 = 0;
		if(k != i)
		{
			j3 = (l - j1 << 16) / (i - k);
			k3 = (k1 - i2 << 15) / (i - k);
		}
		if(i <= j && i <= k)
		{
			if(i >= DrawingArea.bottomY)
				return;
			if(j > DrawingArea.bottomY)
				j = DrawingArea.bottomY;
			if(k > DrawingArea.bottomY)
				k = DrawingArea.bottomY;
			if(j < k)
			{
				j1 = l <<= 16;
				i2 = k1 <<= 15;
				if(i < 0)
				{
					j1 -= j3 * i;
					l -= j2 * i;
					i2 -= k3 * i;
					k1 -= k2 * i;
					i = 0;
				}
				i1 <<= 16;
				l1 <<= 15;
				if(j < 0)
				{
					i1 -= l2 * j;
					l1 -= i3 * j;
					j = 0;
				}
				if(i != j && j3 < j2 || i == j && j3 > l2)
				{
					k -= j;
					j -= i;
					for(i = anIntArray1472[i]; --j >= 0; i += DrawingArea.width)
					{
						method375(DrawingArea.pixels, i, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
						j1 += j3;
						l += j2;
						i2 += k3;
						k1 += k2;
					}

					while(--k >= 0) 
					{
						method375(DrawingArea.pixels, i, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
						j1 += j3;
						i1 += l2;
						i2 += k3;
						l1 += i3;
						i += DrawingArea.width;
					}
					return;
				}
				k -= j;
				j -= i;
				for(i = anIntArray1472[i]; --j >= 0; i += DrawingArea.width)
				{
					method375(DrawingArea.pixels, i, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
					j1 += j3;
					l += j2;
					i2 += k3;
					k1 += k2;
				}

				while(--k >= 0) 
				{
					method375(DrawingArea.pixels, i, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
					j1 += j3;
					i1 += l2;
					i2 += k3;
					l1 += i3;
					i += DrawingArea.width;
				}
				return;
			}
			i1 = l <<= 16;
			l1 = k1 <<= 15;
			if(i < 0)
			{
				i1 -= j3 * i;
				l -= j2 * i;
				l1 -= k3 * i;
				k1 -= k2 * i;
				i = 0;
			}
			j1 <<= 16;
			i2 <<= 15;
			if(k < 0)
			{
				j1 -= l2 * k;
				i2 -= i3 * k;
				k = 0;
			}
			if(i != k && j3 < j2 || i == k && l2 > j2)
			{
				j -= k;
				k -= i;
				for(i = anIntArray1472[i]; --k >= 0; i += DrawingArea.width)
				{
					method375(DrawingArea.pixels, i, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
					i1 += j3;
					l += j2;
					l1 += k3;
					k1 += k2;
				}

				while(--j >= 0) 
				{
					method375(DrawingArea.pixels, i, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
					j1 += l2;
					l += j2;
					i2 += i3;
					k1 += k2;
					i += DrawingArea.width;
				}
				return;
			}
			j -= k;
			k -= i;
			for(i = anIntArray1472[i]; --k >= 0; i += DrawingArea.width)
			{
				method375(DrawingArea.pixels, i, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
				i1 += j3;
				l += j2;
				l1 += k3;
				k1 += k2;
			}

			while(--j >= 0) 
			{
				method375(DrawingArea.pixels, i, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
				j1 += l2;
				l += j2;
				i2 += i3;
				k1 += k2;
				i += DrawingArea.width;
			}
			return;
		}
		if(j <= k)
		{
			if(j >= DrawingArea.bottomY)
				return;
			if(k > DrawingArea.bottomY)
				k = DrawingArea.bottomY;
			if(i > DrawingArea.bottomY)
				i = DrawingArea.bottomY;
			if(k < i)
			{
				l = i1 <<= 16;
				k1 = l1 <<= 15;
				if(j < 0)
				{
					l -= j2 * j;
					i1 -= l2 * j;
					k1 -= k2 * j;
					l1 -= i3 * j;
					j = 0;
				}
				j1 <<= 16;
				i2 <<= 15;
				if(k < 0)
				{
					j1 -= j3 * k;
					i2 -= k3 * k;
					k = 0;
				}
				if(j != k && j2 < l2 || j == k && j2 > j3)
				{
					i -= k;
					k -= j;
					for(j = anIntArray1472[j]; --k >= 0; j += DrawingArea.width)
					{
						method375(DrawingArea.pixels, j, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
						l += j2;
						i1 += l2;
						k1 += k2;
						l1 += i3;
					}

					while(--i >= 0) 
					{
						method375(DrawingArea.pixels, j, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
						l += j2;
						j1 += j3;
						k1 += k2;
						i2 += k3;
						j += DrawingArea.width;
					}
					return;
				}
				i -= k;
				k -= j;
				for(j = anIntArray1472[j]; --k >= 0; j += DrawingArea.width)
				{
					method375(DrawingArea.pixels, j, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
					l += j2;
					i1 += l2;
					k1 += k2;
					l1 += i3;
				}

				while(--i >= 0) 
				{
					method375(DrawingArea.pixels, j, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
					l += j2;
					j1 += j3;
					k1 += k2;
					i2 += k3;
					j += DrawingArea.width;
				}
				return;
			}
			j1 = i1 <<= 16;
			i2 = l1 <<= 15;
			if(j < 0)
			{
				j1 -= j2 * j;
				i1 -= l2 * j;
				i2 -= k2 * j;
				l1 -= i3 * j;
				j = 0;
			}
			l <<= 16;
			k1 <<= 15;
			if(i < 0)
			{
				l -= j3 * i;
				k1 -= k3 * i;
				i = 0;
			}
			if(j2 < l2)
			{
				k -= i;
				i -= j;
				for(j = anIntArray1472[j]; --i >= 0; j += DrawingArea.width)
				{
					method375(DrawingArea.pixels, j, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
					j1 += j2;
					i1 += l2;
					i2 += k2;
					l1 += i3;
				}

				while(--k >= 0) 
				{
					method375(DrawingArea.pixels, j, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
					l += j3;
					i1 += l2;
					k1 += k3;
					l1 += i3;
					j += DrawingArea.width;
				}
				return;
			}
			k -= i;
			i -= j;
			for(j = anIntArray1472[j]; --i >= 0; j += DrawingArea.width)
			{
				method375(DrawingArea.pixels, j, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
				j1 += j2;
				i1 += l2;
				i2 += k2;
				l1 += i3;
			}

			while(--k >= 0) 
			{
				method375(DrawingArea.pixels, j, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
				l += j3;
				i1 += l2;
				k1 += k3;
				l1 += i3;
				j += DrawingArea.width;
			}
			return;
		}
		if(k >= DrawingArea.bottomY)
			return;
		if(i > DrawingArea.bottomY)
			i = DrawingArea.bottomY;
		if(j > DrawingArea.bottomY)
			j = DrawingArea.bottomY;
		if(i < j)
		{
			i1 = j1 <<= 16;
			l1 = i2 <<= 15;
			if(k < 0)
			{
				i1 -= l2 * k;
				j1 -= j3 * k;
				l1 -= i3 * k;
				i2 -= k3 * k;
				k = 0;
			}
			l <<= 16;
			k1 <<= 15;
			if(i < 0)
			{
				l -= j2 * i;
				k1 -= k2 * i;
				i = 0;
			}
			if(l2 < j3)
			{
				j -= i;
				i -= k;
				for(k = anIntArray1472[k]; --i >= 0; k += DrawingArea.width)
				{
					method375(DrawingArea.pixels, k, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
					i1 += l2;
					j1 += j3;
					l1 += i3;
					i2 += k3;
				}

				while(--j >= 0) 
				{
					method375(DrawingArea.pixels, k, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
					i1 += l2;
					l += j2;
					l1 += i3;
					k1 += k2;
					k += DrawingArea.width;
				}
				return;
			}
			j -= i;
			i -= k;
			for(k = anIntArray1472[k]; --i >= 0; k += DrawingArea.width)
			{
				method375(DrawingArea.pixels, k, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
				i1 += l2;
				j1 += j3;
				l1 += i3;
				i2 += k3;
			}

			while(--j >= 0) 
			{
				method375(DrawingArea.pixels, k, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
				i1 += l2;
				l += j2;
				l1 += i3;
				k1 += k2;
				k += DrawingArea.width;
			}
			return;
		}
		l = j1 <<= 16;
		k1 = i2 <<= 15;
		if(k < 0)
		{
			l -= l2 * k;
			j1 -= j3 * k;
			k1 -= i3 * k;
			i2 -= k3 * k;
			k = 0;
		}
		i1 <<= 16;
		l1 <<= 15;
		if(j < 0)
		{
			i1 -= j2 * j;
			l1 -= k2 * j;
			j = 0;
		}
		if(l2 < j3)
		{
			i -= j;
			j -= k;
			for(k = anIntArray1472[k]; --j >= 0; k += DrawingArea.width)
			{
				method375(DrawingArea.pixels, k, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
				l += l2;
				j1 += j3;
				k1 += i3;
				i2 += k3;
			}

			while(--i >= 0) 
			{
				method375(DrawingArea.pixels, k, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
				i1 += j2;
				j1 += j3;
				l1 += k2;
				i2 += k3;
				k += DrawingArea.width;
			}
			return;
		}
		i -= j;
		j -= k;
		for(k = anIntArray1472[k]; --j >= 0; k += DrawingArea.width)
		{
			method375(DrawingArea.pixels, k, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
			l += l2;
			j1 += j3;
			k1 += i3;
			i2 += k3;
		}

		while(--i >= 0) 
		{
			method375(DrawingArea.pixels, k, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
			i1 += j2;
			j1 += j3;
			l1 += k2;
			i2 += k3;
			k += DrawingArea.width;
		}
	}

	private static void method375(int ai[], int i, int l, int i1, int j1, int k1)
	{
		int j;//was parameter
		int k;//was parameter
		if(aBoolean1464)
		{
			int l1;
			if(aBoolean1462)
			{
				if(i1 - l > 3)
					l1 = (k1 - j1) / (i1 - l);
				else
					l1 = 0;
				if(i1 > DrawingArea.centerX)
					i1 = DrawingArea.centerX;
				if(l < 0)
				{
					j1 -= l * l1;
					l = 0;
				}
				if(l >= i1)
					return;
				i += l;
				k = i1 - l >> 2;
				l1 <<= 2;
			} else
			{
				if(l >= i1)
					return;
				i += l;
				k = i1 - l >> 2;
				if(k > 0)
					l1 = (k1 - j1) * anIntArray1468[k] >> 15;
				else
					l1 = 0;
			}
			if(anInt1465 == 0)
			{
				while(--k >= 0) 
				{
					j = anIntArray1482[j1 >> 8];
					j1 += l1;
					ai[i++] = j;
					ai[i++] = j;
					ai[i++] = j;
					ai[i++] = j;
				}
				k = i1 - l & 3;
				if(k > 0)
				{
					j = anIntArray1482[j1 >> 8];
					do
						ai[i++] = j;
					while(--k > 0);
					return;
				}
			} else
			{
				int j2 = anInt1465;
				int l2 = 256 - anInt1465;
				while(--k >= 0) 
				{
					j = anIntArray1482[j1 >> 8];
					j1 += l1;
					j = ((j & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((j & 0xff00) * l2 >> 8 & 0xff00);
					ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
					ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
					ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
					ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
				}
				k = i1 - l & 3;
				if(k > 0)
				{
					j = anIntArray1482[j1 >> 8];
					j = ((j & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((j & 0xff00) * l2 >> 8 & 0xff00);
					do
						ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
					while(--k > 0);
				}
			}
			return;
		}
		if(l >= i1)
			return;
		int i2 = (k1 - j1) / (i1 - l);
		if(aBoolean1462)
		{
			if(i1 > DrawingArea.centerX)
				i1 = DrawingArea.centerX;
			if(l < 0)
			{
				j1 -= l * i2;
				l = 0;
			}
			if(l >= i1)
				return;
		}
		i += l;
		k = i1 - l;
		if(anInt1465 == 0)
		{
			do
			{
				final int idx = j1 >> 8;
				if (aBoolean1464 && smoothShading && idx != 0xffff && (j1 & 0xff) != 0) {
					final int rgb1 = anIntArray1482[idx];
					final int rgb2 = anIntArray1482[idx + 1];
					final int a2 = j1 & 0xff;
					final int a1 = 256 - a2;
					ai[i++] = ((rgb1 & 0xff00ff) * a1 + (rgb2 & 0xff00ff) * a2 & 0xff00ff00) +
							((rgb1 & 0xff00) * a1 + (rgb2 & 0xff00) * a2 & 0xff0000) >> 8;
				} else {
					ai[i++] = anIntArray1482[idx];
				}
				j1 += i2;
			} while(--k > 0);
			return;
		}
		int k2 = anInt1465;
		int i3 = 256 - anInt1465;
		do
		{
			j = anIntArray1482[j1 >> 8];
			j1 += i2;
			j = ((j & 0xff00ff) * i3 >> 8 & 0xff00ff) + ((j & 0xff00) * i3 >> 8 & 0xff00);
			ai[i++] = j + ((ai[i] & 0xff00ff) * k2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * k2 >> 8 & 0xff00);
		} while(--k > 0);
	}

	public static void method376(int i, int j, int k, int l, int i1, int j1, int k1)
	{
		int l1 = 0;
		if(j != i)
			l1 = (i1 - l << 16) / (j - i);
		int i2 = 0;
		if(k != j)
			i2 = (j1 - i1 << 16) / (k - j);
		int j2 = 0;
		if(k != i)
			j2 = (l - j1 << 16) / (i - k);
		if(i <= j && i <= k)
		{
			if(i >= DrawingArea.bottomY)
				return;
			if(j > DrawingArea.bottomY)
				j = DrawingArea.bottomY;
			if(k > DrawingArea.bottomY)
				k = DrawingArea.bottomY;
			if(j < k)
			{
				j1 = l <<= 16;
				if(i < 0)
				{
					j1 -= j2 * i;
					l -= l1 * i;
					i = 0;
				}
				i1 <<= 16;
				if(j < 0)
				{
					i1 -= i2 * j;
					j = 0;
				}
				if(i != j && j2 < l1 || i == j && j2 > i2)
				{
					k -= j;
					j -= i;
					for(i = anIntArray1472[i]; --j >= 0; i += DrawingArea.width)
					{
						method377(DrawingArea.pixels, i, k1, j1 >> 16, l >> 16);
						j1 += j2;
						l += l1;
					}

					while(--k >= 0) 
					{
						method377(DrawingArea.pixels, i, k1, j1 >> 16, i1 >> 16);
						j1 += j2;
						i1 += i2;
						i += DrawingArea.width;
					}
					return;
				}
				k -= j;
				j -= i;
				for(i = anIntArray1472[i]; --j >= 0; i += DrawingArea.width)
				{
					method377(DrawingArea.pixels, i, k1, l >> 16, j1 >> 16);
					j1 += j2;
					l += l1;
				}

				while(--k >= 0) 
				{
					method377(DrawingArea.pixels, i, k1, i1 >> 16, j1 >> 16);
					j1 += j2;
					i1 += i2;
					i += DrawingArea.width;
				}
				return;
			}
			i1 = l <<= 16;
			if(i < 0)
			{
				i1 -= j2 * i;
				l -= l1 * i;
				i = 0;
			}
			j1 <<= 16;
			if(k < 0)
			{
				j1 -= i2 * k;
				k = 0;
			}
			if(i != k && j2 < l1 || i == k && i2 > l1)
			{
				j -= k;
				k -= i;
				for(i = anIntArray1472[i]; --k >= 0; i += DrawingArea.width)
				{
					method377(DrawingArea.pixels, i, k1, i1 >> 16, l >> 16);
					i1 += j2;
					l += l1;
				}

				while(--j >= 0) 
				{
					method377(DrawingArea.pixels, i, k1, j1 >> 16, l >> 16);
					j1 += i2;
					l += l1;
					i += DrawingArea.width;
				}
				return;
			}
			j -= k;
			k -= i;
			for(i = anIntArray1472[i]; --k >= 0; i += DrawingArea.width)
			{
				method377(DrawingArea.pixels, i, k1, l >> 16, i1 >> 16);
				i1 += j2;
				l += l1;
			}

			while(--j >= 0) 
			{
				method377(DrawingArea.pixels, i, k1, l >> 16, j1 >> 16);
				j1 += i2;
				l += l1;
				i += DrawingArea.width;
			}
			return;
		}
		if(j <= k)
		{
			if(j >= DrawingArea.bottomY)
				return;
			if(k > DrawingArea.bottomY)
				k = DrawingArea.bottomY;
			if(i > DrawingArea.bottomY)
				i = DrawingArea.bottomY;
			if(k < i)
			{
				l = i1 <<= 16;
				if(j < 0)
				{
					l -= l1 * j;
					i1 -= i2 * j;
					j = 0;
				}
				j1 <<= 16;
				if(k < 0)
				{
					j1 -= j2 * k;
					k = 0;
				}
				if(j != k && l1 < i2 || j == k && l1 > j2)
				{
					i -= k;
					k -= j;
					for(j = anIntArray1472[j]; --k >= 0; j += DrawingArea.width)
					{
						method377(DrawingArea.pixels, j, k1, l >> 16, i1 >> 16);
						l += l1;
						i1 += i2;
					}

					while(--i >= 0) 
					{
						method377(DrawingArea.pixels, j, k1, l >> 16, j1 >> 16);
						l += l1;
						j1 += j2;
						j += DrawingArea.width;
					}
					return;
				}
				i -= k;
				k -= j;
				for(j = anIntArray1472[j]; --k >= 0; j += DrawingArea.width)
				{
					method377(DrawingArea.pixels, j, k1, i1 >> 16, l >> 16);
					l += l1;
					i1 += i2;
				}

				while(--i >= 0) 
				{
					method377(DrawingArea.pixels, j, k1, j1 >> 16, l >> 16);
					l += l1;
					j1 += j2;
					j += DrawingArea.width;
				}
				return;
			}
			j1 = i1 <<= 16;
			if(j < 0)
			{
				j1 -= l1 * j;
				i1 -= i2 * j;
				j = 0;
			}
			l <<= 16;
			if(i < 0)
			{
				l -= j2 * i;
				i = 0;
			}
			if(l1 < i2)
			{
				k -= i;
				i -= j;
				for(j = anIntArray1472[j]; --i >= 0; j += DrawingArea.width)
				{
					method377(DrawingArea.pixels, j, k1, j1 >> 16, i1 >> 16);
					j1 += l1;
					i1 += i2;
				}

				while(--k >= 0) 
				{
					method377(DrawingArea.pixels, j, k1, l >> 16, i1 >> 16);
					l += j2;
					i1 += i2;
					j += DrawingArea.width;
				}
				return;
			}
			k -= i;
			i -= j;
			for(j = anIntArray1472[j]; --i >= 0; j += DrawingArea.width)
			{
				method377(DrawingArea.pixels, j, k1, i1 >> 16, j1 >> 16);
				j1 += l1;
				i1 += i2;
			}

			while(--k >= 0) 
			{
				method377(DrawingArea.pixels, j, k1, i1 >> 16, l >> 16);
				l += j2;
				i1 += i2;
				j += DrawingArea.width;
			}
			return;
		}
		if(k >= DrawingArea.bottomY)
			return;
		if(i > DrawingArea.bottomY)
			i = DrawingArea.bottomY;
		if(j > DrawingArea.bottomY)
			j = DrawingArea.bottomY;
		if(i < j)
		{
			i1 = j1 <<= 16;
			if(k < 0)
			{
				i1 -= i2 * k;
				j1 -= j2 * k;
				k = 0;
			}
			l <<= 16;
			if(i < 0)
			{
				l -= l1 * i;
				i = 0;
			}
			if(i2 < j2)
			{
				j -= i;
				i -= k;
				for(k = anIntArray1472[k]; --i >= 0; k += DrawingArea.width)
				{
					method377(DrawingArea.pixels, k, k1, i1 >> 16, j1 >> 16);
					i1 += i2;
					j1 += j2;
				}

				while(--j >= 0) 
				{
					method377(DrawingArea.pixels, k, k1, i1 >> 16, l >> 16);
					i1 += i2;
					l += l1;
					k += DrawingArea.width;
				}
				return;
			}
			j -= i;
			i -= k;
			for(k = anIntArray1472[k]; --i >= 0; k += DrawingArea.width)
			{
				method377(DrawingArea.pixels, k, k1, j1 >> 16, i1 >> 16);
				i1 += i2;
				j1 += j2;
			}

			while(--j >= 0) 
			{
				method377(DrawingArea.pixels, k, k1, l >> 16, i1 >> 16);
				i1 += i2;
				l += l1;
				k += DrawingArea.width;
			}
			return;
		}
		l = j1 <<= 16;
		if(k < 0)
		{
			l -= i2 * k;
			j1 -= j2 * k;
			k = 0;
		}
		i1 <<= 16;
		if(j < 0)
		{
			i1 -= l1 * j;
			j = 0;
		}
		if(i2 < j2)
		{
			i -= j;
			j -= k;
			for(k = anIntArray1472[k]; --j >= 0; k += DrawingArea.width)
			{
				method377(DrawingArea.pixels, k, k1, l >> 16, j1 >> 16);
				l += i2;
				j1 += j2;
			}

			while(--i >= 0) 
			{
				method377(DrawingArea.pixels, k, k1, i1 >> 16, j1 >> 16);
				i1 += l1;
				j1 += j2;
				k += DrawingArea.width;
			}
			return;
		}
		i -= j;
		j -= k;
		for(k = anIntArray1472[k]; --j >= 0; k += DrawingArea.width)
		{
			method377(DrawingArea.pixels, k, k1, j1 >> 16, l >> 16);
			l += i2;
			j1 += j2;
		}

		while(--i >= 0) 
		{
			method377(DrawingArea.pixels, k, k1, j1 >> 16, i1 >> 16);
			i1 += l1;
			j1 += j2;
			k += DrawingArea.width;
		}
	}

	private static void method377(int ai[], int i, int j, int l, int i1)
	{
		int k;//was parameter
		if(aBoolean1462)
		{
			if(i1 > DrawingArea.centerX)
				i1 = DrawingArea.centerX;
			if(l < 0)
				l = 0;
		}
		if(l >= i1)
			return;
		i += l;
		k = i1 - l >> 2;
		if(anInt1465 == 0)
		{
			while(--k >= 0) 
			{
				ai[i++] = j;
				ai[i++] = j;
				ai[i++] = j;
				ai[i++] = j;
			}
			for(k = i1 - l & 3; --k >= 0;)
				ai[i++] = j;

			return;
		}
		int j1 = anInt1465;
		int k1 = 256 - anInt1465;
		j = ((j & 0xff00ff) * k1 >> 8 & 0xff00ff) + ((j & 0xff00) * k1 >> 8 & 0xff00);
		while(--k >= 0) 
		{
			ai[i] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
			i++;
			ai[i] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
			i++;
			ai[i] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
			i++;
			ai[i] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
			i++;
		}
		for(k = i1 - l & 3; --k >= 0;)
			ai[i] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
		i++;
	}
	
    public static void drawShadedTriangle(int y_a, int y_b, int y_c, int x_a, int x_b, int x_c, int hsl1, int hsl2, int hsl3, float z_a, float z_b, float z_c) {
        if (z_a < 0 || z_b < 0 || z_c < 0)
                return;
        int rgb1 = anIntArray1482[hsl1];
        int rgb2 = anIntArray1482[hsl2];
        int rgb3 = anIntArray1482[hsl3];
        int r1 = rgb1 >> 16 & 0xff;
        int g1 = rgb1 >> 8 & 0xff;
        int b1 = rgb1 & 0xff;
        int r2 = rgb2 >> 16 & 0xff;
        int g2 = rgb2 >> 8 & 0xff;
        int b2 = rgb2 & 0xff;
        int r3 = rgb3 >> 16 & 0xff;
        int g3 = rgb3 >> 8 & 0xff;
        int b3 = rgb3 & 0xff;
        int a_to_b = 0;
        int dr1 = 0;
        int dg1 = 0;
        int db1 = 0;
        if (y_b != y_a) {
                a_to_b = (x_b - x_a << 16) / (y_b - y_a);
                dr1 = (r2 - r1 << 16) / (y_b - y_a);
                dg1 = (g2 - g1 << 16) / (y_b - y_a);
                db1 = (b2 - b1 << 16) / (y_b - y_a);
        }
        int b_to_c = 0;
        int dr2 = 0;
        int dg2 = 0;
        int db2 = 0;
        if (y_c != y_b) {
                b_to_c = (x_c - x_b << 16) / (y_c - y_b);
                dr2 = (r3 - r2 << 16) / (y_c - y_b);
                dg2 = (g3 - g2 << 16) / (y_c - y_b);
                db2 = (b3 - b2 << 16) / (y_c - y_b);
        }
        int c_to_a = 0;
        int dr3 = 0;
        int dg3 = 0;
        int db3 = 0;
        if (y_c != y_a) {
                c_to_a = (x_a - x_c << 16) / (y_a - y_c);
                dr3 = (r1 - r3 << 16) / (y_a - y_c);
                dg3 = (g1 - g3 << 16) / (y_a - y_c);
                db3 = (b1 - b3 << 16) / (y_a - y_c);
        }
        float b_aX = x_b - x_a;
        float b_aY = y_b - y_a;
        float c_aX = x_c - x_a;
        float c_aY = y_c - y_a;
        float b_aZ = z_b - z_a;
        float c_aZ = z_c - z_a;

        float div = b_aX * c_aY - c_aX * b_aY;
        float depth_slope = (b_aZ * c_aY - c_aZ * b_aY) / div;
        float depth_increment = (c_aZ * b_aX - b_aZ * c_aX) / div;
        if(y_a <= y_b && y_a <= y_c) {
                if(y_a >= DrawingArea.bottomY) {
                        return;
                }
                if(y_b > DrawingArea.bottomY) {
                        y_b = DrawingArea.bottomY;
                }
                if(y_c > DrawingArea.bottomY) {
                        y_c = DrawingArea.bottomY;
                }
                z_a = z_a - depth_slope * x_a + depth_slope;
                if(y_b < y_c) {
                        x_c = x_a <<= 16;
                        r3 = r1 <<= 16;
                        g3 = g1 <<= 16;
                        b3 = b1 <<= 16;
                        if(y_a < 0) {
                                x_c -= c_to_a * y_a;
                                x_a -= a_to_b * y_a;
                                r3 -= dr3 * y_a;
                                g3 -= dg3 * y_a;
                                b3 -= db3 * y_a;
                                r1 -= dr1 * y_a;
                                g1 -= dg1 * y_a;
                                b1 -= db1 * y_a;
                                z_a -= depth_increment * y_a;
                                y_a = 0;
                        }
                        x_b <<= 16;
                        r2 <<= 16;
                        g2 <<= 16;
                        b2 <<= 16;
                        if(y_b < 0) {
                                x_b -= b_to_c * y_b;
                                r2 -= dr2 * y_b;
                                g2 -= dg2 * y_b;
                                b2 -= db2 * y_b;
                                y_b = 0;
                        }
                        if(y_a != y_b && c_to_a < a_to_b || y_a == y_b && c_to_a > b_to_c) {
                                y_c -= y_b;
                                y_b -= y_a;
                                for(y_a = anIntArray1472[y_a]; --y_b >= 0; y_a += DrawingArea.width) {
                                        drawShadedScanline(DrawingArea.pixels, y_a, x_c >> 16, x_a >> 16, r3, g3, b3, r1, g1, b1, z_a, depth_slope);
                                        x_c += c_to_a;
                                        x_a += a_to_b;
                                        r3 += dr3;
                                        g3 += dg3;
                                        b3 += db3;
                                        r1 += dr1;
                                        g1 += dg1;
                                        b1 += db1;
                                        z_a += depth_increment;
                                }
                                while(--y_c >= 0) {
                                        drawShadedScanline(DrawingArea.pixels, y_a, x_c >> 16, x_b >> 16, r3, g3, b3, r2, g2, b2, z_a, depth_slope);
                                        x_c += c_to_a;
                                        x_b += b_to_c;
                                        r3 += dr3;
                                        g3 += dg3;
                                        b3 += db3;
                                        r2 += dr2;
                                        g2 += dg2;
                                        b2 += db2;
                                        y_a += DrawingArea.width;
                                        z_a += depth_increment;
                                }
                                return;
                        }
                        y_c -= y_b;
                        y_b -= y_a;
                        for(y_a = anIntArray1472[y_a]; --y_b >= 0; y_a += DrawingArea.width) {
                                drawShadedScanline(DrawingArea.pixels, y_a, x_a >> 16, x_c >> 16, r1, g1, b1, r3, g3, b3, z_a, depth_slope);
                                x_c += c_to_a;
                                x_a += a_to_b;
                                r3 += dr3;
                                g3 += dg3;
                                b3 += db3;
                                r1 += dr1;
                                g1 += dg1;
                                b1 += db1;
                                z_a += depth_increment;
                        }
                        while(--y_c >= 0) {
                                drawShadedScanline(DrawingArea.pixels, y_a, x_b >> 16, x_c >> 16, r2, g2, b2, r3, g3, b3, z_a, depth_slope);
                                x_c += c_to_a;
                                x_b += b_to_c;
                                r3 += dr3;
                                g3 += dg3;
                                b3 += db3;
                                r2 += dr2;
                                g2 += dg2;
                                b2 += db2;
                                y_a += DrawingArea.width;
                                z_a += depth_increment;
                        }
                        return;
                }
                x_b = x_a <<= 16;
                r2 = r1 <<= 16;
                g2 = g1 <<= 16;
                b2 = b1 <<= 16;
                if(y_a < 0) {
                        x_b -= c_to_a * y_a;
                        x_a -= a_to_b * y_a;
                        r2 -= dr3 * y_a;
                        g2 -= dg3 * y_a;
                        b2 -= db3 * y_a;
                        r1 -= dr1 * y_a;
                        g1 -= dg1 * y_a;
                        b1 -= db1 * y_a;
                        z_a -= depth_increment * y_a;
                        y_a = 0;
                }
                x_c <<= 16;
                r3 <<= 16;
                g3 <<= 16;
                b3 <<= 16;
                if(y_c < 0) {
                        x_c -= b_to_c * y_c;
                        r3 -= dr2 * y_c;
                        g3 -= dg2 * y_c;
                        b3 -= db2 * y_c;
                        y_c = 0;
                }
                if(y_a != y_c && c_to_a < a_to_b || y_a == y_c && b_to_c > a_to_b) {
                        y_b -= y_c;
                        y_c -= y_a;
                        for(y_a = anIntArray1472[y_a]; --y_c >= 0; y_a += DrawingArea.width) {
                                drawShadedScanline(DrawingArea.pixels, y_a, x_b >> 16, x_a >> 16, r2, g2, b2, r1, g1, b1, z_a, depth_slope);
                                x_b += c_to_a;
                                x_a += a_to_b;
                                r2 += dr3;
                                g2 += dg3;
                                b2 += db3;
                                r1 += dr1;
                                g1 += dg1;
                                b1 += db1;
                                z_a += depth_increment;
                        }
                        while(--y_b >= 0) {
                                drawShadedScanline(DrawingArea.pixels, y_a, x_c >> 16, x_a >> 16, r3, g3, b3, r1, g1, b1, z_a, depth_slope);
                                x_c += b_to_c;
                                x_a += a_to_b;
                                r3 += dr2;
                                g3 += dg2;
                                b3 += db2;
                                r1 += dr1;
                                g1 += dg1;
                                b1 += db1;
                                y_a += DrawingArea.width;
                                z_a += depth_increment;
                        }
                        return;
                }
                y_b -= y_c;
                y_c -= y_a;
                for(y_a = anIntArray1472[y_a]; --y_c >= 0; y_a += DrawingArea.width) {
                        drawShadedScanline(DrawingArea.pixels, y_a, x_a >> 16, x_b >> 16, r1, g1, b1, r2, g2, b2, z_a, depth_slope);
                        x_b += c_to_a;
                        x_a += a_to_b;
                        r2 += dr3;
                        g2 += dg3;
                        b2 += db3;
                        r1 += dr1;
                        g1 += dg1;
                        b1 += db1;
                        z_a += depth_increment;
                }
                while(--y_b >= 0) {
                        drawShadedScanline(DrawingArea.pixels, y_a, x_a >> 16, x_c >> 16, r1, g1, b1, r3, g3, b3, z_a, depth_slope);
                        x_c += b_to_c;
                        x_a += a_to_b;
                        r3 += dr2;
                        g3 += dg2;
                        b3 += db2;
                        r1 += dr1;
                        g1 += dg1;
                        b1 += db1;
                        y_a += DrawingArea.width;
                        z_a += depth_increment;
                }
                return;
        }
        if(y_b <= y_c) {
                if(y_b >= DrawingArea.bottomY) {
                        return;
                }
                if(y_c > DrawingArea.bottomY) {
                        y_c = DrawingArea.bottomY;
                }
                if(y_a > DrawingArea.bottomY) {
                        y_a = DrawingArea.bottomY;
                }
                z_b = z_b - depth_slope * x_b + depth_slope;
                if(y_c < y_a) {
                        x_a = x_b <<= 16;
                        r1 = r2 <<= 16;
                        g1 = g2 <<= 16;
                        b1 = b2 <<= 16;
                        if(y_b < 0) {
                                x_a -= a_to_b * y_b;
                                x_b -= b_to_c * y_b;
                                r1 -= dr1 * y_b;
                                g1 -= dg1 * y_b;
                                b1 -= db1 * y_b;
                                r2 -= dr2 * y_b;
                                g2 -= dg2 * y_b;
                                b2 -= db2 * y_b;
                                z_b -= depth_increment * y_b;
                                y_b = 0;
                        }
                        x_c <<= 16;
                        r3 <<= 16;
                        g3 <<= 16;
                        b3 <<= 16;
                        if(y_c < 0) {
                                x_c -= c_to_a * y_c;
                                r3 -= dr3 * y_c;
                                g3 -= dg3 * y_c;
                                b3 -= db3 * y_c;
                                y_c = 0;
                        }
                        if(y_b != y_c && a_to_b < b_to_c || y_b == y_c && a_to_b > c_to_a) {
                                y_a -= y_c;
                                y_c -= y_b;
                                for(y_b = anIntArray1472[y_b]; --y_c >= 0; y_b += DrawingArea.width) {
                                        drawShadedScanline(DrawingArea.pixels, y_b, x_a >> 16, x_b >> 16, r1, g1, b1, r2, g2, b2, z_b, depth_slope);
                                        x_a += a_to_b;
                                        x_b += b_to_c;
                                        r1 += dr1;
                                        g1 += dg1;
                                        b1 += db1;
                                        r2 += dr2;
                                        g2 += dg2;
                                        b2 += db2;
                                        z_b += depth_increment;
                                }
                                while(--y_a >= 0) {
                                        drawShadedScanline(DrawingArea.pixels, y_b, x_a >> 16, x_c >> 16, r1, g1, b1, r3, g3, b3, z_b, depth_slope);
                                        x_a += a_to_b;
                                        x_c += c_to_a;
                                        r1 += dr1;
                                        g1 += dg1;
                                        b1 += db1;
                                        r3 += dr3;
                                        g3 += dg3;
                                        b3 += db3;
                                        y_b += DrawingArea.width;
                                        z_b += depth_increment;
                                }
                                return;
                        }
                        y_a -= y_c;
                        y_c -= y_b;
                        for(y_b = anIntArray1472[y_b]; --y_c >= 0; y_b += DrawingArea.width) {
                                drawShadedScanline(DrawingArea.pixels, y_b, x_b >> 16, x_a >> 16, r2, g2, b2, r1, g1, b1, z_b, depth_slope);
                                x_a += a_to_b;
                                x_b += b_to_c;
                                r1 += dr1;
                                g1 += dg1;
                                b1 += db1;
                                r2 += dr2;
                                g2 += dg2;
                                b2 += db2;
                                z_b += depth_increment;
                        }
                        while(--y_a >= 0) {
                                drawShadedScanline(DrawingArea.pixels, y_b, x_c >> 16, x_a >> 16, r3, g3, b3, r1, g1, b1, z_b, depth_slope);
                                x_a += a_to_b;
                                x_c += c_to_a;
                                r1 += dr1;
                                g1 += dg1;
                                b1 += db1;
                                r3 += dr3;
                                g3 += dg3;
                                b3 += db3;
                                y_b += DrawingArea.width;
                                z_b += depth_increment;
                        }
                        return;
                }
                x_c = x_b <<= 16;
                r3 = r2 <<= 16;
                g3 = g2 <<= 16;
                b3 = b2 <<= 16;
                if(y_b < 0) {
                        x_c -= a_to_b * y_b;
                        x_b -= b_to_c * y_b;
                        r3 -= dr1 * y_b;
                        g3 -= dg1 * y_b;
                        b3 -= db1 * y_b;
                        r2 -= dr2 * y_b;
                        g2 -= dg2 * y_b;
                        b2 -= db2 * y_b;
                        z_b -= depth_increment * y_b;
                        y_b = 0;
                }
                x_a <<= 16;
                r1 <<= 16;
                g1 <<= 16;
                b1 <<= 16;
                if(y_a < 0) {
                        x_a -= c_to_a * y_a;
                        r1 -= dr3 * y_a;
                        g1 -= dg3 * y_a;
                        b1 -= db3 * y_a;
                        y_a = 0;
                }
                if(a_to_b < b_to_c) {
                        y_c -= y_a;
                        y_a -= y_b;
                        for(y_b = anIntArray1472[y_b]; --y_a >= 0; y_b += DrawingArea.width) {
                                drawShadedScanline(DrawingArea.pixels, y_b, x_c >> 16, x_b >> 16, r3, g3, b3, r2, g2, b2, z_b, depth_slope);
                                x_c += a_to_b;
                                x_b += b_to_c;
                                r3 += dr1;
                                g3 += dg1;
                                b3 += db1;
                                r2 += dr2;
                                g2 += dg2;
                                b2 += db2;
                                z_b += depth_increment;
                        }
                        while(--y_c >= 0) {
                                drawShadedScanline(DrawingArea.pixels, y_b, x_a >> 16, x_b >> 16, r1, g1, b1, r2, g2, b2, z_b, depth_slope);
                                x_a += c_to_a;
                                x_b += b_to_c;
                                r1 += dr3;
                                g1 += dg3;
                                b1 += db3;
                                r2 += dr2;
                                g2 += dg2;
                                b2 += db2;
                                y_b += DrawingArea.width;
                                z_b += depth_increment;
                        }
                        return;
                }
                y_c -= y_a;
                y_a -= y_b;
                for(y_b = anIntArray1472[y_b]; --y_a >= 0; y_b += DrawingArea.width) {
                        drawShadedScanline(DrawingArea.pixels, y_b, x_b >> 16, x_c >> 16, r2, g2, b2, r3, g3, b3, z_b, depth_slope);
                        x_c += a_to_b;
                        x_b += b_to_c;
                        r3 += dr1;
                        g3 += dg1;
                        b3 += db1;
                        r2 += dr2;
                        g2 += dg2;
                        b2 += db2;
                        z_b += depth_increment;
                }
                while(--y_c >= 0) {
                        drawShadedScanline(DrawingArea.pixels, y_b, x_b >> 16, x_a >> 16, r2, g2, b2, r1, g1, b1, z_b, depth_slope);
                        x_a += c_to_a;
                        x_b += b_to_c;
                        r1 += dr3;
                        g1 += dg3;
                        b1 += db3;
                        r2 += dr2;
                        g2 += dg2;
                        b2 += db2;
                        y_b += DrawingArea.width;
                        z_b += depth_increment;
                }
                return;
        }
        if(y_c >= DrawingArea.bottomY) {
                return;
        }
        if(y_a > DrawingArea.bottomY) {
                y_a = DrawingArea.bottomY;
        }
        if(y_b > DrawingArea.bottomY) {
                y_b = DrawingArea.bottomY;
        }
        z_c = z_c - depth_slope * x_c + depth_slope;
        if(y_a < y_b) {
                x_b = x_c <<= 16;
                r2 = r3 <<= 16;
                g2 = g3 <<= 16;
                b2 = b3 <<= 16;
                if(y_c < 0) {
                        x_b -= b_to_c * y_c;
                        x_c -= c_to_a * y_c;
                        r2 -= dr2 * y_c;
                        g2 -= dg2 * y_c;
                        b2 -= db2 * y_c;
                        r3 -= dr3 * y_c;
                        g3 -= dg3 * y_c;
                        b3 -= db3 * y_c;
                        z_c -= depth_increment * y_c;
                        y_c = 0;
                }
                x_a <<= 16;
                r1 <<= 16;
                g1 <<= 16;
                b1 <<= 16;
                if(y_a < 0) {
                        x_a -= a_to_b * y_a;
                        r1 -= dr1 * y_a;
                        g1 -= dg1 * y_a;
                        b1 -= db1 * y_a;
                        y_a = 0;
                }
                if(b_to_c < c_to_a) {
                        y_b -= y_a;
                        y_a -= y_c;
                        for(y_c = anIntArray1472[y_c]; --y_a >= 0; y_c += DrawingArea.width) {
                                drawShadedScanline(DrawingArea.pixels, y_c, x_b >> 16, x_c >> 16, r2, g2, b2, r3, g3, b3, z_c, depth_slope);
                                x_b += b_to_c;
                                x_c += c_to_a;
                                r2 += dr2;
                                g2 += dg2;
                                b2 += db2;
                                r3 += dr3;
                                g3 += dg3;
                                b3 += db3;
                                z_c += depth_increment;
                        }
                        while(--y_b >= 0) {
                                drawShadedScanline(DrawingArea.pixels, y_c, x_b >> 16, x_a >> 16, r2, g2, b2, r1, g1, b1, z_c, depth_slope);
                                x_b += b_to_c;
                                x_a += a_to_b;
                                r2 += dr2;
                                g2 += dg2;
                                b2 += db2;
                                r1 += dr1;
                                g1 += dg1;
                                b1 += db1;
                                y_c += DrawingArea.width;
                                z_c += depth_increment;
                        }
                        return;
                }
                y_b -= y_a;
                y_a -= y_c;
                for(y_c = anIntArray1472[y_c]; --y_a >= 0; y_c += DrawingArea.width) {
                        drawShadedScanline(DrawingArea.pixels, y_c, x_c >> 16, x_b >> 16, r3, g3, b3, r2, g2, b2, z_c, depth_slope);
                        x_b += b_to_c;
                        x_c += c_to_a;
                        r2 += dr2;
                        g2 += dg2;
                        b2 += db2;
                        r3 += dr3;
                        g3 += dg3;
                        b3 += db3;
                        z_c += depth_increment;
                }
                while(--y_b >= 0) {
                        drawShadedScanline(DrawingArea.pixels, y_c, x_a >> 16, x_b >> 16, r1, g1, b1, r2, g2, b2, z_c, depth_slope);
                        x_b += b_to_c;
                        x_a += a_to_b;
                        r2 += dr2;
                        g2 += dg2;
                        b2 += db2;
                        r1 += dr1;
                        g1 += dg1;
                        b1 += db1;
                        z_c += depth_increment;
                        y_c += DrawingArea.width;
                }
                return;
        }
        x_a = x_c <<= 16;
        r1 = r3 <<= 16;
        g1 = g3 <<= 16;
        b1 = b3 <<= 16;
        if(y_c < 0) {
                x_a -= b_to_c * y_c;
                x_c -= c_to_a * y_c;
                r1 -= dr2 * y_c;
                g1 -= dg2 * y_c;
                b1 -= db2 * y_c;
                r3 -= dr3 * y_c;
                g3 -= dg3 * y_c;
                b3 -= db3 * y_c;
                z_c -= depth_increment * y_c;
                y_c = 0;
        }
        x_b <<= 16;
        r2 <<= 16;
        g2 <<= 16;
        b2 <<= 16;
        if(y_b < 0) {
                x_b -= a_to_b * y_b;
                r2 -= dr1 * y_b;
                g2 -= dg1 * y_b;
                b2 -= db1 * y_b;
                y_b = 0;
        }
        if(b_to_c < c_to_a) {
                y_a -= y_b;
                y_b -= y_c;
                for(y_c = anIntArray1472[y_c]; --y_b >= 0; y_c += DrawingArea.width) {
                        drawShadedScanline(DrawingArea.pixels, y_c, x_a >> 16, x_c >> 16, r1, g1, b1, r3, g3, b3, z_c, depth_slope);
                        x_a += b_to_c;
                        x_c += c_to_a;
                        r1 += dr2;
                        g1 += dg2;
                        b1 += db2;
                        r3 += dr3;
                        g3 += dg3;
                        b3 += db3;
                        z_c += depth_increment;
                }
                while(--y_a >= 0) {
                        drawShadedScanline(DrawingArea.pixels, y_c, x_b >> 16, x_c >> 16, r2, g2, b2, r3, g3, b3, z_c, depth_slope);
                        x_b += a_to_b;
                        x_c += c_to_a;
                        r2 += dr1;
                        g2 += dg1;
                        b2 += db1;
                        r3 += dr3;
                        g3 += dg3;
                        b3 += db3;
                        z_c += depth_increment;
                        y_c += DrawingArea.width;
                }
                return;
        }
        y_a -= y_b;
        y_b -= y_c;
        for(y_c = anIntArray1472[y_c]; --y_b >= 0; y_c += DrawingArea.width) {
                drawShadedScanline(DrawingArea.pixels, y_c, x_c >> 16, x_a >> 16, r3, g3, b3, r1, g1, b1, z_c, depth_slope);
                x_a += b_to_c;
                x_c += c_to_a;
                r1 += dr2;
                g1 += dg2;
                b1 += db2;
                r3 += dr3;
                g3 += dg3;
                b3 += db3;
                z_c += depth_increment;
        }
        while(--y_a >= 0) {
                drawShadedScanline(DrawingArea.pixels, y_c, x_c >> 16, x_b >> 16, r3, g3, b3, r2, g2, b2, z_c, depth_slope);
                x_b += a_to_b;
                x_c += c_to_a;
                r2 += dr1;
                g2 += dg1;
                b2 += db1;
                r3 += dr3;
                g3 += dg3;
                b3 += db3;
                y_c += DrawingArea.width;
                z_c += depth_increment;
        }
}

public static void drawShadedScanline(int[] dest, int offset, int x1, int x2, int r1, int g1, int b1, int r2, int g2, int b2, float depth, float depth_slope) {
        int n = x2 - x1;
        if (n <= 0) {
                return;
        }
        r2 = (r2 - r1) / n;
        g2 = (g2 - g1) / n;
        b2 = (b2 - b1) / n;
        if (aBoolean1462) {
                if (x2 > DrawingArea.centerX) {
                        n -= x2 - DrawingArea.centerX;
                        x2 = DrawingArea.centerX;
                }
                if (x1 < 0) {
                        n = x2;
                        r1 -= x1 * r2;
                        g1 -= x1 * g2;
                        b1 -= x1 * b2;
                        x1 = 0;
                }
        }
        if (x1 < x2) {
                offset += x1;
                depth += depth_slope * (float) x1;
                if (anInt1465 == 0) {
                        while (--n >= 0) {
                                if (true) {
                                        dest[offset] = (r1 & 0xff0000) | (g1 >> 8 & 0xff00) | (b1 >> 16 & 0xff);
                                        DrawingArea.depthBuffer[offset] = depth;
                                }
                                depth += depth_slope;
                                r1 += r2;
                                g1 += g2;
                                b1 += b2;
                                offset++;
                        }
                } else {
                        final int a1 = anInt1465;
                        final int a2 = 256 - anInt1465;
                        int rgb;
                        int dst;
                        while (--n >= 0) {
                                rgb = (r1 & 0xff0000) | (g1 >> 8 & 0xff00) | (b1 >> 16 & 0xff);
                                rgb = ((rgb & 0xff00ff) * a2 >> 8 & 0xff00ff) + ((rgb & 0xff00) * a2 >> 8 & 0xff00);
                                dst = dest[offset];
                                if (true) {
                                        dest[offset] = rgb + ((dst & 0xff00ff) * a1 >> 8 & 0xff00ff) + ((dst & 0xff00) * a1 >> 8 & 0xff00);
                                        DrawingArea.depthBuffer[offset] = depth;
                                }
                                depth += depth_slope;
                                r1 += r2;
                                g1 += g2;
                                b1 += b2;
                                offset++;
                        }
                }
        }
}

public static void drawFlatTriangle(int y_a, int y_b, int y_c, int x_a, int x_b, int x_c, int k1, float z_a, float z_b, float z_c)
{
        if (z_a < 0 || z_b < 0 || z_c < 0) return;
        int a_to_b = 0;
        if(y_b != y_a)
                a_to_b = (x_b - x_a << 16) / (y_b - y_a);
        int b_to_c = 0;
        if(y_c != y_b)
                b_to_c = (x_c - x_b << 16) / (y_c - y_b);
        int c_to_a = 0;
        if(y_c != y_a)
                c_to_a = (x_a - x_c << 16) / (y_a - y_c);
        float b_aX = x_b - x_a;
        float b_aY = y_b - y_a;
        float c_aX = x_c - x_a;
        float c_aY = y_c - y_a;
        float b_aZ = z_b - z_a;
        float c_aZ = z_c - z_a;

        float div = b_aX * c_aY - c_aX * b_aY;
        float depth_slope = (b_aZ * c_aY - c_aZ * b_aY) / div;
        float depth_increment = (c_aZ * b_aX - b_aZ * c_aX) / div;
        if(y_a <= y_b && y_a <= y_c)
        {
                if(y_a >= DrawingArea.bottomY)
                        return;
                if(y_b > DrawingArea.bottomY)
                        y_b = DrawingArea.bottomY;
                if(y_c > DrawingArea.bottomY)
                        y_c = DrawingArea.bottomY;
                z_a = z_a - depth_slope * x_a + depth_slope;
                if(y_b < y_c)
                {
                        x_c = x_a <<= 16;
                        if(y_a < 0)
                        {
                                x_c -= c_to_a * y_a;
                                x_a -= a_to_b * y_a;
                                z_a -= depth_increment * y_a;
                                y_a = 0;
                        }
                        x_b <<= 16;
                        if(y_b < 0)
                        {
                                x_b -= b_to_c * y_b;
                                y_b = 0;
                        }
                        if(y_a != y_b && c_to_a < a_to_b || y_a == y_b && c_to_a > b_to_c)
                        {
                                y_c -= y_b;
                                y_b -= y_a;
                                for(y_a = anIntArray1472[y_a]; --y_b >= 0; y_a += DrawingArea.width)
                                {
                                        drawScanLine(DrawingArea.pixels, y_a, k1, x_c >> 16, x_a >> 16, z_a, depth_slope);
                                        x_c += c_to_a;
                                        x_a += a_to_b;
                                        z_a += depth_increment;
                                }

                                while(--y_c >= 0)
                                {
                                        drawScanLine(DrawingArea.pixels, y_a, k1, x_c >> 16, x_b >> 16, z_a, depth_slope);
                                        x_c += c_to_a;
                                        x_b += b_to_c;
                                        y_a += DrawingArea.width;
                                        z_a += depth_increment;
                                }
                                return;
                        }
                        y_c -= y_b;
                        y_b -= y_a;
                        for(y_a = anIntArray1472[y_a]; --y_b >= 0; y_a += DrawingArea.width)
                        {
                                drawScanLine(DrawingArea.pixels, y_a, k1, x_a >> 16, x_c >> 16, z_a, depth_slope);
                                x_c += c_to_a;
                                x_a += a_to_b;
                                z_a += depth_increment;
                        }

                        while(--y_c >= 0)
                        {
                                drawScanLine(DrawingArea.pixels, y_a, k1, x_b >> 16, x_c >> 16, z_a, depth_slope);
                                x_c += c_to_a;
                                x_b += b_to_c;
                                y_a += DrawingArea.width;
                                z_a += depth_increment;
                        }
                        return;
                }
                x_b = x_a <<= 16;
                if(y_a < 0)
                {
                        x_b -= c_to_a * y_a;
                        x_a -= a_to_b * y_a;
                        z_a -= depth_increment * y_a;
                        y_a = 0;
                       
                }
                x_c <<= 16;
                if(y_c < 0)
                {
                        x_c -= b_to_c * y_c;
                        y_c = 0;
                }
                if(y_a != y_c && c_to_a < a_to_b || y_a == y_c && b_to_c > a_to_b)
                {
                        y_b -= y_c;
                        y_c -= y_a;
                        for(y_a = anIntArray1472[y_a]; --y_c >= 0; y_a += DrawingArea.width)
                        {
                                drawScanLine(DrawingArea.pixels, y_a, k1, x_b >> 16, x_a >> 16, z_a, depth_slope);
                                z_a += depth_increment;
                                x_b += c_to_a;
                                x_a += a_to_b;
                        }

                        while(--y_b >= 0)
                        {
                                drawScanLine(DrawingArea.pixels, y_a, k1, x_c >> 16, x_a >> 16, z_a, depth_slope);
                                z_a += depth_increment;
                                x_c += b_to_c;
                                x_a += a_to_b;
                                y_a += DrawingArea.width;
                        }
                        return;
                }
                y_b -= y_c;
                y_c -= y_a;
                for(y_a = anIntArray1472[y_a]; --y_c >= 0; y_a += DrawingArea.width)
                {
                        drawScanLine(DrawingArea.pixels, y_a, k1, x_a >> 16, x_b >> 16, z_a, depth_slope);
                        z_a += depth_increment;
                        x_b += c_to_a;
                        x_a += a_to_b;
                }

                while(--y_b >= 0)
                {
                        drawScanLine(DrawingArea.pixels, y_a, k1, x_a >> 16, x_c >> 16, z_a, depth_slope);
                        z_a += depth_increment;
                        x_c += b_to_c;
                        x_a += a_to_b;
                        y_a += DrawingArea.width;
                }
                return;
        }
        if(y_b <= y_c)
        {
                if(y_b >= DrawingArea.bottomY)
                        return;
                if(y_c > DrawingArea.bottomY)
                        y_c = DrawingArea.bottomY;
                if(y_a > DrawingArea.bottomY)
                        y_a = DrawingArea.bottomY;
                z_b = z_b - depth_slope * x_b + depth_slope;
                if(y_c < y_a)
                {
                        x_a = x_b <<= 16;
                        if(y_b < 0)
                        {
                                x_a -= a_to_b * y_b;
                                x_b -= b_to_c * y_b;
                                z_b -= depth_increment * y_b;
                                y_b = 0;
                        }
                        x_c <<= 16;
                        if(y_c < 0)
                        {
                                x_c -= c_to_a * y_c;
                                y_c = 0;
                        }
                        if(y_b != y_c && a_to_b < b_to_c || y_b == y_c && a_to_b > c_to_a)
                        {
                                y_a -= y_c;
                                y_c -= y_b;
                                for(y_b = anIntArray1472[y_b]; --y_c >= 0; y_b += DrawingArea.width)
                                {
                                        drawScanLine(DrawingArea.pixels, y_b, k1, x_a >> 16, x_b >> 16, z_b, depth_slope);
                                        z_b += depth_increment;
                                        x_a += a_to_b;
                                        x_b += b_to_c;
                                }

                                while(--y_a >= 0)
                                {
                                        drawScanLine(DrawingArea.pixels, y_b, k1, x_a >> 16, x_c >> 16, z_b, depth_slope);
                                        z_b += depth_increment;
                                        x_a += a_to_b;
                                        x_c += c_to_a;
                                        y_b += DrawingArea.width;
                                }
                                return;
                        }
                        y_a -= y_c;
                        y_c -= y_b;
                        for(y_b = anIntArray1472[y_b]; --y_c >= 0; y_b += DrawingArea.width)
                        {
                                drawScanLine(DrawingArea.pixels, y_b, k1, x_b >> 16, x_a >> 16, z_b, depth_slope);
                                z_b += depth_increment;
                                x_a += a_to_b;
                                x_b += b_to_c;
                        }

                        while(--y_a >= 0)
                        {
                                drawScanLine(DrawingArea.pixels, y_b, k1, x_c >> 16, x_a >> 16, z_b, depth_slope);
                                z_b += depth_increment;
                                x_a += a_to_b;
                                x_c += c_to_a;
                                y_b += DrawingArea.width;
                        }
                        return;
                }
                x_c = x_b <<= 16;
                if(y_b < 0)
                {
                        x_c -= a_to_b * y_b;
                        x_b -= b_to_c * y_b;
                        z_b -= depth_increment * y_b;
                        y_b = 0;
                }
                x_a <<= 16;
                if(y_a < 0)
                {
                        x_a -= c_to_a * y_a;
                        y_a = 0;
                }
                if(a_to_b < b_to_c)
                {
                        y_c -= y_a;
                        y_a -= y_b;
                        for(y_b = anIntArray1472[y_b]; --y_a >= 0; y_b += DrawingArea.width)
                        {
                                drawScanLine(DrawingArea.pixels, y_b, k1, x_c >> 16, x_b >> 16, z_b, depth_slope);
                                z_b += depth_increment;
                                x_c += a_to_b;
                                x_b += b_to_c;
                        }

                        while(--y_c >= 0)
                        {
                                drawScanLine(DrawingArea.pixels, y_b, k1, x_a >> 16, x_b >> 16, z_b, depth_slope);
                                z_b += depth_increment;
                                x_a += c_to_a;
                                x_b += b_to_c;
                                y_b += DrawingArea.width;
                        }
                        return;
                }
                y_c -= y_a;
                y_a -= y_b;
                for(y_b = anIntArray1472[y_b]; --y_a >= 0; y_b += DrawingArea.width)
                {
                        drawScanLine(DrawingArea.pixels, y_b, k1, x_b >> 16, x_c >> 16, z_b, depth_slope);
                        z_b += depth_increment;
                        x_c += a_to_b;
                        x_b += b_to_c;
                }

                while(--y_c >= 0)
                {
                        drawScanLine(DrawingArea.pixels, y_b, k1, x_b >> 16, x_a >> 16, z_b, depth_slope);
                        z_b += depth_increment;
                        x_a += c_to_a;
                        x_b += b_to_c;
                        y_b += DrawingArea.width;
                }
                return;
        }
        if(y_c >= DrawingArea.bottomY)
                return;
        if(y_a > DrawingArea.bottomY)
                y_a = DrawingArea.bottomY;
        if(y_b > DrawingArea.bottomY)
                y_b = DrawingArea.bottomY;
        z_c = z_c - depth_slope * x_c + depth_slope;
        if(y_a < y_b)
        {
                x_b = x_c <<= 16;
                if(y_c < 0)
                {
                        x_b -= b_to_c * y_c;
                        x_c -= c_to_a * y_c;
                        z_c -= depth_increment * y_c;
                        y_c = 0;
                }
                x_a <<= 16;
                if(y_a < 0)
                {
                        x_a -= a_to_b * y_a;
                        y_a = 0;
                }
                if(b_to_c < c_to_a)
                {
                        y_b -= y_a;
                        y_a -= y_c;
                        for(y_c = anIntArray1472[y_c]; --y_a >= 0; y_c += DrawingArea.width)
                        {
                                drawScanLine(DrawingArea.pixels, y_c, k1, x_b >> 16, x_c >> 16, z_c, depth_slope);
                                z_c += depth_increment;
                                x_b += b_to_c;
                                x_c += c_to_a;
                        }

                        while(--y_b >= 0)
                        {
                                drawScanLine(DrawingArea.pixels, y_c, k1, x_b >> 16, x_a >> 16, z_c, depth_slope);
                                z_c += depth_increment;
                                x_b += b_to_c;
                                x_a += a_to_b;
                                y_c += DrawingArea.width;
                        }
                        return;
                }
                y_b -= y_a;
                y_a -= y_c;
                for(y_c = anIntArray1472[y_c]; --y_a >= 0; y_c += DrawingArea.width)
                {
                        drawScanLine(DrawingArea.pixels, y_c, k1, x_c >> 16, x_b >> 16, z_c, depth_slope);
                        z_c += depth_increment;
                        x_b += b_to_c;
                        x_c += c_to_a;
                }

                while(--y_b >= 0)
                {
                        drawScanLine(DrawingArea.pixels, y_c, k1, x_a >> 16, x_b >> 16, z_c, depth_slope);
                        z_c += depth_increment;
                        x_b += b_to_c;
                        x_a += a_to_b;
                        y_c += DrawingArea.width;
                }
                return;
        }
        x_a = x_c <<= 16;
        if(y_c < 0)
        {
                x_a -= b_to_c * y_c;
                x_c -= c_to_a * y_c;
                z_c -= depth_increment * y_c;
                y_c = 0;
        }
        x_b <<= 16;
        if(y_b < 0)
        {
                x_b -= a_to_b * y_b;
                y_b = 0;
        }
        if(b_to_c < c_to_a)
        {
                y_a -= y_b;
                y_b -= y_c;
                for(y_c = anIntArray1472[y_c]; --y_b >= 0; y_c += DrawingArea.width)
                {
                        drawScanLine(DrawingArea.pixels, y_c, k1, x_a >> 16, x_c >> 16, z_c, depth_slope);
                        z_c += depth_increment;
                        x_a += b_to_c;
                        x_c += c_to_a;
                }

                while(--y_a >= 0)
                {
                        drawScanLine(DrawingArea.pixels, y_c, k1, x_b >> 16, x_c >> 16, z_c, depth_slope);
                        z_c += depth_increment;
                        x_b += a_to_b;
                        x_c += c_to_a;
                        y_c += DrawingArea.width;
                }
                return;
        }
        y_a -= y_b;
        y_b -= y_c;
        for(y_c = anIntArray1472[y_c]; --y_b >= 0; y_c += DrawingArea.width)
        {
                drawScanLine(DrawingArea.pixels, y_c, k1, x_c >> 16, x_a >> 16, z_c, depth_slope);
                z_c += depth_increment;
                x_a += b_to_c;
                x_c += c_to_a;
        }

        while(--y_a >= 0)
        {
                drawScanLine(DrawingArea.pixels, y_c, k1, x_c >> 16, x_b >> 16, z_c, depth_slope);
                z_c += depth_increment;
                x_b += a_to_b;
                x_c += c_to_a;
                y_c += DrawingArea.width;
        }
}

private static void drawScanLine(int dest[], int dest_off, int loops, int start_x, int end_x, float depth, float depth_slope)
{
        int rgb;
        if(aBoolean1462)
        {
                if(end_x > DrawingArea.centerX)
                        end_x = DrawingArea.centerX;
                if(start_x < 0)
                        start_x = 0;
        }
        if(start_x >= end_x)
                return;
        dest_off += start_x;
        rgb = end_x - start_x >> 2;
        depth += depth_slope * (float) start_x;
        if(anInt1465 == 0)
        {
                while(--rgb >= 0)
                {
                        for (int i = 0; i < 4; i++) {
                                if (true) {
                                        dest[dest_off] = loops;
                                        DrawingArea.depthBuffer[dest_off] = depth;
                                }
                                dest_off++;
                                depth += depth_slope;
                        }
                }
                for(rgb = end_x - start_x & 3; --rgb >= 0;) {
                        if (true) {
                                dest[dest_off] = loops;
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        dest_off++;
                        depth += depth_slope;
                }
                return;
        }
        int dest_alpha = anInt1465;
        int src_alpha = 256 - anInt1465;
        loops = ((loops & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((loops & 0xff00) * src_alpha >> 8 & 0xff00);
        while(--rgb >= 0)
        {
                for (int i = 0; i < 4; i++) {
                        if (true) {
                                dest[dest_off] = loops + ((dest[dest_off] & 0xff00ff) * dest_alpha >> 8 & 0xff00ff) + ((dest[dest_off] & 0xff00) * dest_alpha >> 8 & 0xff00);
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        dest_off++;
                        depth += depth_slope;
                }
        }
        for(rgb = end_x - start_x & 3; --rgb >= 0;) {
                if (true) {
                        dest[dest_off] = loops + ((dest[dest_off] & 0xff00ff) * dest_alpha >> 8 & 0xff00ff) + ((dest[dest_off] & 0xff00) * dest_alpha >> 8 & 0xff00);
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
                dest_off++;
                depth += depth_slope;
        }
}

private static void drawFlatTexturedScanline(int dest[], int dest_off, int loops, int start_x, int end_x, float depth, float depth_slope) {
        int rgb;
        if(aBoolean1462) {
                if(end_x > DrawingArea.centerX)
                        end_x = DrawingArea.centerX;
                if(start_x < 0)
                        start_x = 0;
        }
        if(start_x >= end_x)
                return;
        dest_off += start_x;
        rgb = end_x - start_x >> 2;
        depth += depth_slope * (float) start_x;
        if(anInt1465 == 0)
        {
                while(--rgb >= 0)
                {
                        for (int i = 0; i < 4; i++) {
                                if (true) {
                                        dest[dest_off] = loops;
                                        DrawingArea.depthBuffer[dest_off] = depth;
                                }
                                dest_off++;
                                depth += depth_slope;
                        }
                }
                for(rgb = end_x - start_x & 3; --rgb >= 0;) {
                        if (true) {
                                dest[dest_off] = loops;
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        dest_off++;
                        depth += depth_slope;
                }
                return;
        }
        int dest_alpha = anInt1465;
        int src_alpha = 256 - anInt1465;
        loops = ((loops & 0xff00ff) * src_alpha >> 8 & 0xff00ff) + ((loops & 0xff00) * src_alpha >> 8 & 0xff00);
        while(--rgb >= 0)
        {
                for (int i = 0; i < 4; i++) {
                        if (true) {
                                dest[dest_off] = loops + ((dest[dest_off] & 0xff00ff) * dest_alpha >> 8 & 0xff00ff) + ((dest[dest_off] & 0xff00) * dest_alpha >> 8 & 0xff00);
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        dest_off++;
                        depth += depth_slope;
                }
        }
        for(rgb = end_x - start_x & 3; --rgb >= 0;) {
                if (true) {
                        dest[dest_off] = loops + ((dest[dest_off] & 0xff00ff) * dest_alpha >> 8 & 0xff00ff) + ((dest[dest_off] & 0xff00) * dest_alpha >> 8 & 0xff00);
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
                dest_off++;
                depth += depth_slope;
        }
}

public static void drawTexturedTriangle(int y_a, int y_b, int y_c, int x_a, int x_b, int x_c, int k1, int l1, int i2, int Px, int Mx, int Nx, int Pz, int Mz, int Nz,  int Py, int My, int Ny, int k4, float z_a, float z_b, float z_c) {
        if (z_a < 0 || z_b < 0 || z_c < 0)
                return;
        int texture[] = method371(k4);
        aBoolean1463 = !aBooleanArray1475[k4];
        Mx = Px - Mx;
        Mz = Pz - Mz;
        My = Py - My;
        Nx -= Px;
        Nz -= Pz;
        Ny -= Py;
        int Oa = Nx * Pz - Nz * Px << 14;
        int Ha = Nz * Py - Ny * Pz << 8;
        int Va = Ny * Px - Nx * Py << 5;
        int Ob = Mx * Pz - Mz * Px << 14;
        int Hb = Mz * Py - My * Pz << 8;
        int Vb = My * Px - Mx * Py << 5;
        int Oc = Mz * Nx - Mx * Nz << 14;
        int Hc = My * Nz - Mz * Ny << 8;
        int Vc = Mx * Ny - My * Nx << 5;
        int a_to_b = 0;
        int grad_a_off = 0;
        if(y_b != y_a)
        {
                a_to_b = (x_b - x_a << 16) / (y_b - y_a);
                grad_a_off = (l1 - k1 << 16) / (y_b - y_a);
        }
        int b_to_c = 0;
        int grad_b_off = 0;
        if(y_c != y_b)
        {
                b_to_c = (x_c - x_b << 16) / (y_c - y_b);
                grad_b_off = (i2 - l1 << 16) / (y_c - y_b);
        }
        int c_to_a = 0;
        int grad_c_off = 0;
        if(y_c != y_a)
        {
                c_to_a = (x_a - x_c << 16) / (y_a - y_c);
                grad_c_off = (k1 - i2 << 16) / (y_a - y_c);
        }
        float b_aX = x_b - x_a;
        float b_aY = y_b - y_a;
        float c_aX = x_c - x_a;
        float c_aY = y_c - y_a;
        float b_aZ = z_b - z_a;
        float c_aZ = z_c - z_a;

        float div = b_aX * c_aY - c_aX * b_aY;
        float depth_slope = (b_aZ * c_aY - c_aZ * b_aY) / div;
        float depth_increment = (c_aZ * b_aX - b_aZ * c_aX) / div;
        if(y_a <= y_b && y_a <= y_c)
        {
                if(y_a >= DrawingArea.bottomY)
                        return;
                if(y_b > DrawingArea.bottomY)
                        y_b = DrawingArea.bottomY;
                if(y_c > DrawingArea.bottomY)
                        y_c = DrawingArea.bottomY;
                z_a = z_a - depth_slope * x_a + depth_slope;
                if(y_b < y_c)
                {
                        x_c = x_a <<= 16;
                        i2 = k1 <<= 16;
                        if(y_a < 0)
                        {
                                x_c -= c_to_a * y_a;
                                x_a -= a_to_b * y_a;
                                z_a -= depth_increment * y_a;
                                i2 -= grad_c_off * y_a;
                                k1 -= grad_a_off * y_a;
                                y_a = 0;
                        }
                        x_b <<= 16;
                        l1 <<= 16;
                        if(y_b < 0)
                        {
                                x_b -= b_to_c * y_b;
                                l1 -= grad_b_off * y_b;
                                y_b = 0;
                        }
                        int k8 = y_a - textureInt2;
                        Oa += Va * k8;
                        Ob += Vb * k8;
                        Oc += Vc * k8;
                        if(y_a != y_b && c_to_a < a_to_b || y_a == y_b && c_to_a > b_to_c)
                        {
                                y_c -= y_b;
                                y_b -= y_a;
                                y_a = anIntArray1472[y_a];
                                while(--y_b >= 0)
                                {
                                        drawTexturedScanline(DrawingArea.pixels, texture, y_a, x_c >> 16, x_a >> 16, i2 >> 8, k1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_a, depth_slope);
                                        x_c += c_to_a;
                                        x_a += a_to_b;
                                        z_a += depth_increment;
                                        i2 += grad_c_off;
                                        k1 += grad_a_off;
                                        y_a += DrawingArea.width;
                                        Oa += Va;
                                        Ob += Vb;
                                        Oc += Vc;
                                }
                                while(--y_c >= 0)
                                {
                                        drawTexturedScanline(DrawingArea.pixels, texture, y_a, x_c >> 16, x_b >> 16, i2 >> 8, l1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_a, depth_slope);
                                        x_c += c_to_a;
                                        x_b += b_to_c;
                                        z_a += depth_increment;
                                        i2 += grad_c_off;
                                        l1 += grad_b_off;
                                        y_a += DrawingArea.width;
                                        Oa += Va;
                                        Ob += Vb;
                                        Oc += Vc;
                                }
                                return;
                        }
                        y_c -= y_b;
                        y_b -= y_a;
                        y_a = anIntArray1472[y_a];
                        while(--y_b >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_a, x_a >> 16, x_c >> 16, k1 >> 8, i2 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_a, depth_slope);
                                x_c += c_to_a;
                                x_a += a_to_b;
                                z_a += depth_increment;
                                i2 += grad_c_off;
                                k1 += grad_a_off;
                                y_a += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        while(--y_c >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_a, x_b >> 16, x_c >> 16, l1 >> 8, i2 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_a, depth_slope);
                                x_c += c_to_a;
                                x_b += b_to_c;
                                z_a += depth_increment;
                                i2 += grad_c_off;
                                l1 += grad_b_off;
                                y_a += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        return;
                }
                x_b = x_a <<= 16;
                l1 = k1 <<= 16;
                if(y_a < 0)
                {
                        x_b -= c_to_a * y_a;
                        x_a -= a_to_b * y_a;
                        z_a -= depth_increment * y_a;
                        l1 -= grad_c_off * y_a;
                        k1 -= grad_a_off * y_a;
                        y_a = 0;
                }
                x_c <<= 16;
                i2 <<= 16;
                if(y_c < 0)
                {
                        x_c -= b_to_c * y_c;
                        i2 -= grad_b_off * y_c;
                        y_c = 0;
                }
                int l8 = y_a - textureInt2;
                Oa += Va * l8;
                Ob += Vb * l8;
                Oc += Vc * l8;
                if(y_a != y_c && c_to_a < a_to_b || y_a == y_c && b_to_c > a_to_b)
                {
                        y_b -= y_c;
                        y_c -= y_a;
                        y_a = anIntArray1472[y_a];
                        while(--y_c >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_a, x_b >> 16, x_a >> 16, l1 >> 8, k1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_a, depth_slope);
                                x_b += c_to_a;
                                x_a += a_to_b;
                                l1 += grad_c_off;
                                k1 += grad_a_off;
                                z_a += depth_increment;
                                y_a += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        while(--y_b >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_a, x_c >> 16, x_a >> 16, i2 >> 8, k1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_a, depth_slope);
                                x_c += b_to_c;
                                x_a += a_to_b;
                                i2 += grad_b_off;
                                k1 += grad_a_off;
                                z_a += depth_increment;
                                y_a += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        return;
                }
                y_b -= y_c;
                y_c -= y_a;
                y_a = anIntArray1472[y_a];
                while(--y_c >= 0)
                {
                        drawTexturedScanline(DrawingArea.pixels, texture, y_a, x_a >> 16, x_b >> 16, k1 >> 8, l1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_a, depth_slope);
                        x_b += c_to_a;
                        x_a += a_to_b;
                        l1 += grad_c_off;
                        k1 += grad_a_off;
                        z_a += depth_increment;
                        y_a += DrawingArea.width;
                        Oa += Va;
                        Ob += Vb;
                        Oc += Vc;
                }
                while(--y_b >= 0)
                {
                        drawTexturedScanline(DrawingArea.pixels, texture, y_a, x_a >> 16, x_c >> 16, k1 >> 8, i2 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_a, depth_slope);
                        x_c += b_to_c;
                        x_a += a_to_b;
                        i2 += grad_b_off;
                        k1 += grad_a_off;
                        z_a += depth_increment;
                        y_a += DrawingArea.width;
                        Oa += Va;
                        Ob += Vb;
                        Oc += Vc;
                }
                return;
        }
        if(y_b <= y_c)
        {
                if(y_b >= DrawingArea.bottomY)
                        return;
                if(y_c > DrawingArea.bottomY)
                        y_c = DrawingArea.bottomY;
                if(y_a > DrawingArea.bottomY)
                        y_a = DrawingArea.bottomY;
                z_b = z_b - depth_slope * x_b + depth_slope;
                if(y_c < y_a)
                {
                        x_a = x_b <<= 16;
                        k1 = l1 <<= 16;
                        if(y_b < 0)
                        {
                                x_a -= a_to_b * y_b;
                                x_b -= b_to_c * y_b;
                                z_b -= depth_increment * y_b;
                                k1 -= grad_a_off * y_b;
                                l1 -= grad_b_off * y_b;
                                y_b = 0;
                        }
                        x_c <<= 16;
                        i2 <<= 16;
                        if(y_c < 0)
                        {
                                x_c -= c_to_a * y_c;
                                i2 -= grad_c_off * y_c;
                                y_c = 0;
                        }
                        int i9 = y_b - textureInt2;
                        Oa += Va * i9;
                        Ob += Vb * i9;
                        Oc += Vc * i9;
                        if(y_b != y_c && a_to_b < b_to_c || y_b == y_c && a_to_b > c_to_a)
                        {
                                y_a -= y_c;
                                y_c -= y_b;
                                y_b = anIntArray1472[y_b];
                                while(--y_c >= 0)
                                {
                                        drawTexturedScanline(DrawingArea.pixels, texture, y_b, x_a >> 16, x_b >> 16, k1 >> 8, l1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_b, depth_slope);
                                        x_a += a_to_b;
                                        x_b += b_to_c;
                                        k1 += grad_a_off;
                                        l1 += grad_b_off;
                                        z_b += depth_increment;
                                        y_b += DrawingArea.width;
                                        Oa += Va;
                                        Ob += Vb;
                                        Oc += Vc;
                                }
                                while(--y_a >= 0)
                                {
                                        drawTexturedScanline(DrawingArea.pixels, texture, y_b, x_a >> 16, x_c >> 16, k1 >> 8, i2 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_b, depth_slope);
                                        x_a += a_to_b;
                                        x_c += c_to_a;
                                        k1 += grad_a_off;
                                        i2 += grad_c_off;
                                        z_b += depth_increment;
                                        y_b += DrawingArea.width;
                                        Oa += Va;
                                        Ob += Vb;
                                        Oc += Vc;
                                }
                                return;
                        }
                        y_a -= y_c;
                        y_c -= y_b;
                        y_b = anIntArray1472[y_b];
                        while(--y_c >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_b, x_b >> 16, x_a >> 16, l1 >> 8, k1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_b, depth_slope);
                                x_a += a_to_b;
                                x_b += b_to_c;
                                k1 += grad_a_off;
                                l1 += grad_b_off;
                                z_b += depth_increment;
                                y_b += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        while(--y_a >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_b, x_c >> 16, x_a >> 16, i2 >> 8, k1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_b, depth_slope);
                                x_a += a_to_b;
                                x_c += c_to_a;
                                k1 += grad_a_off;
                                i2 += grad_c_off;
                                z_b += depth_increment;
                                y_b += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        return;
                }
                x_c = x_b <<= 16;
                i2 = l1 <<= 16;
                if(y_b < 0)
                {
                        x_c -= a_to_b * y_b;
                        x_b -= b_to_c * y_b;
                        z_b -= depth_increment * y_b;
                        i2 -= grad_a_off * y_b;
                        l1 -= grad_b_off * y_b;
                        y_b = 0;
                }
                x_a <<= 16;
                k1 <<= 16;
                if(y_a < 0)
                {
                        x_a -= c_to_a * y_a;
                        k1 -= grad_c_off * y_a;
                        y_a = 0;
                }
                int j9 = y_b - textureInt2;
                Oa += Va * j9;
                Ob += Vb * j9;
                Oc += Vc * j9;
                if(a_to_b < b_to_c)
                {
                        y_c -= y_a;
                        y_a -= y_b;
                        y_b = anIntArray1472[y_b];
                        while(--y_a >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_b, x_c >> 16, x_b >> 16, i2 >> 8, l1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_b, depth_slope);
                                x_c += a_to_b;
                                x_b += b_to_c;
                                i2 += grad_a_off;
                                l1 += grad_b_off;
                                z_b += depth_increment;
                                y_b += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        while(--y_c >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_b, x_a >> 16, x_b >> 16, k1 >> 8, l1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_b, depth_slope);
                                x_a += c_to_a;
                                x_b += b_to_c;
                                k1 += grad_c_off;
                                l1 += grad_b_off;
                                z_b += depth_increment;
                                y_b += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        return;
                }
                y_c -= y_a;
                y_a -= y_b;
                y_b = anIntArray1472[y_b];
                while(--y_a >= 0)
                {
                        drawTexturedScanline(DrawingArea.pixels, texture, y_b, x_b >> 16, x_c >> 16, l1 >> 8, i2 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_b, depth_slope);
                        x_c += a_to_b;
                        x_b += b_to_c;
                        i2 += grad_a_off;
                        l1 += grad_b_off;
                        z_b += depth_increment;
                        y_b += DrawingArea.width;
                        Oa += Va;
                        Ob += Vb;
                        Oc += Vc;
                }
                while(--y_c >= 0)
                {
                        drawTexturedScanline(DrawingArea.pixels, texture, y_b, x_b >> 16, x_a >> 16, l1 >> 8, k1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_b, depth_slope);
                        x_a += c_to_a;
                        x_b += b_to_c;
                        k1 += grad_c_off;
                        l1 += grad_b_off;
                        z_b += depth_increment;
                        y_b += DrawingArea.width;
                        Oa += Va;
                        Ob += Vb;
                        Oc += Vc;
                }
                return;
        }
        if(y_c >= DrawingArea.bottomY)
                return;
        if(y_a > DrawingArea.bottomY)
                y_a = DrawingArea.bottomY;
        if(y_b > DrawingArea.bottomY)
                y_b = DrawingArea.bottomY;
        z_c = z_c - depth_slope * x_c + depth_slope;
        if(y_a < y_b)
        {
                x_b = x_c <<= 16;
                l1 = i2 <<= 16;
                if(y_c < 0)
                {
                        x_b -= b_to_c * y_c;
                        x_c -= c_to_a * y_c;
                        z_c -= depth_increment * y_c;
                        l1 -= grad_b_off * y_c;
                        i2 -= grad_c_off * y_c;
                        y_c = 0;
                }
                x_a <<= 16;
                k1 <<= 16;
                if(y_a < 0)
                {
                        x_a -= a_to_b * y_a;
                        k1 -= grad_a_off * y_a;
                        y_a = 0;
                }
                int k9 = y_c - textureInt2;
                Oa += Va * k9;
                Ob += Vb * k9;
                Oc += Vc * k9;
                if(b_to_c < c_to_a)
                {
                        y_b -= y_a;
                        y_a -= y_c;
                        y_c = anIntArray1472[y_c];
                        while(--y_a >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_c, x_b >> 16, x_c >> 16, l1 >> 8, i2 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_c, depth_slope);
                                x_b += b_to_c;
                                x_c += c_to_a;
                                l1 += grad_b_off;
                                i2 += grad_c_off;
                                z_c += depth_increment;
                                y_c += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        while(--y_b >= 0)
                        {
                                drawTexturedScanline(DrawingArea.pixels, texture, y_c, x_b >> 16, x_a >> 16, l1 >> 8, k1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_c, depth_slope);
                                x_b += b_to_c;
                                x_a += a_to_b;
                                l1 += grad_b_off;
                                k1 += grad_a_off;
                                z_c += depth_increment;
                                y_c += DrawingArea.width;
                                Oa += Va;
                                Ob += Vb;
                                Oc += Vc;
                        }
                        return;
                }
                y_b -= y_a;
                y_a -= y_c;
                y_c = anIntArray1472[y_c];
                while(--y_a >= 0)
                {
                        drawTexturedScanline(DrawingArea.pixels, texture, y_c, x_c >> 16, x_b >> 16, i2 >> 8, l1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_c, depth_slope);
                        x_b += b_to_c;
                        x_c += c_to_a;
                        l1 += grad_b_off;
                        i2 += grad_c_off;
                        z_c += depth_increment;
                        y_c += DrawingArea.width;
                        Oa += Va;
                        Ob += Vb;
                        Oc += Vc;
                }
                while(--y_b >= 0)
                {
                        drawTexturedScanline(DrawingArea.pixels, texture, y_c, x_a >> 16, x_b >> 16, k1 >> 8, l1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_c, depth_slope);
                        x_b += b_to_c;
                        x_a += a_to_b;
                        l1 += grad_b_off;
                        k1 += grad_a_off;
                        z_c += depth_increment;
                        y_c += DrawingArea.width;
                        Oa += Va;
                        Ob += Vb;
                        Oc += Vc;
                }
                return;
        }
        x_a = x_c <<= 16;
        k1 = i2 <<= 16;
        if(y_c < 0)
        {
                x_a -= b_to_c * y_c;
                x_c -= c_to_a * y_c;
                z_c -= depth_increment * y_c;
                k1 -= grad_b_off * y_c;
                i2 -= grad_c_off * y_c;
                y_c = 0;
        }
        x_b <<= 16;
        l1 <<= 16;
        if(y_b < 0)
        {
                x_b -= a_to_b * y_b;
                l1 -= grad_a_off * y_b;
                y_b = 0;
        }
        int l9 = y_c - textureInt2;
        Oa += Va * l9;
        Ob += Vb * l9;
        Oc += Vc * l9;
        if(b_to_c < c_to_a)
        {
                y_a -= y_b;
                y_b -= y_c;
                y_c = anIntArray1472[y_c];
                while(--y_b >= 0)
                {
                        drawTexturedScanline(DrawingArea.pixels, texture, y_c, x_a >> 16, x_c >> 16, k1 >> 8, i2 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_c, depth_slope);
                        x_a += b_to_c;
                        x_c += c_to_a;
                        k1 += grad_b_off;
                        i2 += grad_c_off;
                        z_c += depth_increment;
                        y_c += DrawingArea.width;
                        Oa += Va;
                        Ob += Vb;
                        Oc += Vc;
                }
                while(--y_a >= 0)
                {
                        drawTexturedScanline(DrawingArea.pixels, texture, y_c, x_b >> 16, x_c >> 16, l1 >> 8, i2 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_c, depth_slope);
                        x_b += a_to_b;
                        x_c += c_to_a;
                        l1 += grad_a_off;
                        i2 += grad_c_off;
                        z_c += depth_increment;
                        y_c += DrawingArea.width;
                        Oa += Va;
                        Ob += Vb;
                        Oc += Vc;
                }
                return;
        }
        y_a -= y_b;
        y_b -= y_c;
        y_c = anIntArray1472[y_c];
        while(--y_b >= 0)
        {
                drawTexturedScanline(DrawingArea.pixels, texture, y_c, x_c >> 16, x_a >> 16, i2 >> 8, k1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_c, depth_slope);
                x_a += b_to_c;
                x_c += c_to_a;
                k1 += grad_b_off;
                i2 += grad_c_off;
                z_c += depth_increment;
                y_c += DrawingArea.width;
                Oa += Va;
                Ob += Vb;
                Oc += Vc;
        }
        while(--y_a >= 0)
        {
                drawTexturedScanline(DrawingArea.pixels, texture, y_c, x_c >> 16, x_b >> 16, i2 >> 8, l1 >> 8, Oa, Ob, Oc, Ha, Hb, Hc, z_c, depth_slope);
                x_b += a_to_b;
                x_c += c_to_a;
                l1 += grad_a_off;
                i2 += grad_c_off;
                z_c += depth_increment;
                y_c += DrawingArea.width;
                Oa += Va;
                Ob += Vb;
                Oc += Vc;
        }
}

public static void drawTexturedScanline(int dest[], int texture[], int dest_off, int start_x, int end_x, int shadeValue, int gradient, int l1, int i2, int j2, int k2, int l2, int i3, float depth, float depth_slope) {
    int rgb = 0;
    int loops = 0;
    if (start_x >= end_x)
        return;
    int j3;
    int k3;
    if (aBoolean1462) {
        j3 = (gradient - shadeValue) / (end_x - start_x);
        if (end_x > DrawingArea.centerX)
            end_x = DrawingArea.centerX;
        if (start_x < 0) {
            shadeValue -= start_x * j3;
            start_x = 0;
        }
        if (start_x >= end_x)
            return;
        k3 = end_x - start_x >> 3;
        j3 <<= 12;
        shadeValue <<= 9;
    } else {
        if (end_x - start_x > 7) {
            k3 = end_x - start_x >> 3;
            j3 = (gradient - shadeValue) * anIntArray1468[k3] >> 6;
        } else {
            k3 = 0;
            j3 = 0;
        }
        shadeValue <<= 9;
    }
    dest_off += start_x;
    depth += depth_slope * (float) start_x;
    if (lowMem) {
        int i4 = 0;
        int k4 = 0;
        int k6 = start_x - textureInt1;
        l1 += (k2 >> 3) * k6;
        i2 += (l2 >> 3) * k6;
        j2 += (i3 >> 3) * k6;
        int i5 = j2 >> 12;
        if (i5 != 0) {
            rgb = l1 / i5;
            loops = i2 / i5;
            if (rgb < 0)
                rgb = 0;
            else
            if (rgb > 4032)
                rgb = 4032;
        }
        l1 += k2;
        i2 += l2;
        j2 += i3;
        i5 = j2 >> 12;
        if (i5 != 0) {
            i4 = l1 / i5;
            k4 = i2 / i5;
            if (i4 < 7)
                i4 = 7;
            else
            if (i4 > 4032)
                i4 = 4032;
        }
        int i7 = i4 - rgb >> 3;
        int k7 = k4 - loops >> 3;
        rgb += (shadeValue & 0x600000) >> 3;
        int i8 = shadeValue >> 23;
        if (aBoolean1463) {
            while (k3-- > 0) {
                for (int i = 0; i < 8; i++) {
                        if (true) {
                                dest[dest_off] = texture[(loops & 0xfc0) + (rgb >> 6)] >>> i8;
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        dest_off++;
                        depth += depth_slope;
                        rgb += i7;
                        loops += k7;
                }
                rgb = i4;
                loops = k4;
                l1 += k2;
                i2 += l2;
                j2 += i3;
                int j5 = j2 >> 12;
                if (j5 != 0) {
                    i4 = l1 / j5;
                    k4 = i2 / j5;
                    if (i4 < 7)
                        i4 = 7;
                    else
                    if (i4 > 4032)
                        i4 = 4032;
                }
                i7 = i4 - rgb >> 3;
                k7 = k4 - loops >> 3;
                shadeValue += j3;
                rgb += (shadeValue & 0x600000) >> 3;
                i8 = shadeValue >> 23;
            }
            for (k3 = end_x - start_x & 7; k3-- > 0;) {
                if (true) {
                        dest[dest_off] = texture[(loops & 0xfc0) + (rgb >> 6)] >>> i8;
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
                dest_off++;
                depth += depth_slope;
                rgb += i7;
                loops += k7;
            }

            return;
        }
        while (k3-- > 0) {
            int k8;
            for (int i = 0; i < 8; i++) {
                    if ((k8 = texture[(loops & 0xfc0) + (rgb >> 6)] >>> i8) != 0 && true) {
                        dest[dest_off] = k8;
                        DrawingArea.depthBuffer[dest_off] = depth;
                    }
                    dest_off++;
                    depth += depth_slope;
                    rgb += i7;
                    loops += k7;
            }
           
            rgb = i4;
            loops = k4;
            l1 += k2;
            i2 += l2;
            j2 += i3;
            int k5 = j2 >> 12;
            if (k5 != 0) {
                i4 = l1 / k5;
                k4 = i2 / k5;
                if (i4 < 7)
                    i4 = 7;
                else
                if (i4 > 4032)
                    i4 = 4032;
            }
            i7 = i4 - rgb >> 3;
            k7 = k4 - loops >> 3;
            shadeValue += j3;
            rgb += (shadeValue & 0x600000) >> 3;
            i8 = shadeValue >> 23;
        }
        for (k3 = end_x - start_x & 7; k3-- > 0;) {
            int l8;
            if ((l8 = texture[(loops & 0xfc0) + (rgb >> 6)] >>> i8) != 0 && true) {
                dest[dest_off] = l8;
                DrawingArea.depthBuffer[dest_off] = depth;
            }
            dest_off++;
            depth += depth_slope;
            rgb += i7;
            loops += k7;
        }

        return;
    }
    int j4 = 0;
    int l4 = 0;
    int l6 = start_x - textureInt1;
    l1 += (k2 >> 3) * l6;
    i2 += (l2 >> 3) * l6;
    j2 += (i3 >> 3) * l6;
    int l5 = j2 >> 14;
    if (l5 != 0) {
        rgb = l1 / l5;
        loops = i2 / l5;
        if (rgb < 0)
            rgb = 0;
        else
        if (rgb > 16256)
            rgb = 16256;
    }
    l1 += k2;
    i2 += l2;
    j2 += i3;
    l5 = j2 >> 14;
    if (l5 != 0) {
        j4 = l1 / l5;
        l4 = i2 / l5;
        if (j4 < 7)
            j4 = 7;
        else
        if (j4 > 16256)
            j4 = 16256;
    }
    int j7 = j4 - rgb >> 3;
    int l7 = l4 - loops >> 3;
    rgb += shadeValue & 0x600000;
    int j8 = shadeValue >> 23;
    if (aBoolean1463) {
        while (k3-- > 0) {
                for (int i = 0; i < 8; i++) {
                        if (true) {
                                dest[dest_off] = texture[(loops & 0x3f80) + (rgb >> 7)] >>> j8;
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        depth += depth_slope;
                    dest_off++;
                    rgb += j7;
                    loops += l7;
                }
            rgb = j4;
            loops = l4;
            l1 += k2;
            i2 += l2;
            j2 += i3;
            int i6 = j2 >> 14;
            if (i6 != 0) {
                j4 = l1 / i6;
                l4 = i2 / i6;
                if (j4 < 7)
                    j4 = 7;
                else
                if (j4 > 16256)
                    j4 = 16256;
            }
            j7 = j4 - rgb >> 3;
            l7 = l4 - loops >> 3;
            shadeValue += j3;
            rgb += shadeValue & 0x600000;
            j8 = shadeValue >> 23;
        }
        for (k3 = end_x - start_x & 7; k3-- > 0;) {
                if (true) {
                        dest[dest_off] = texture[(loops & 0x3f80) + (rgb >> 7)] >>> j8;
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
            dest_off++;
            depth += depth_slope;
            rgb += j7;
            loops += l7;
        }

        return;
    }
    while (k3-- > 0) {
        int i9;
        for (int i = 0; i < 8; i++) {
                if ((i9 = texture[(loops & 0x3f80) + (rgb >> 7)] >>> j8) != 0 && true) {
                    dest[dest_off] = i9;
                    DrawingArea.depthBuffer[dest_off] = depth;
                }
                dest_off++;
                depth += depth_slope;
                rgb += j7;
                loops += l7;
        }
        rgb = j4;
        loops = l4;
        l1 += k2;
        i2 += l2;
        j2 += i3;
        int j6 = j2 >> 14;
        if (j6 != 0) {
            j4 = l1 / j6;
            l4 = i2 / j6;
            if (j4 < 7)
                j4 = 7;
            else
            if (j4 > 16256)
                j4 = 16256;
        }
        j7 = j4 - rgb >> 3;
        l7 = l4 - loops >> 3;
        shadeValue += j3;
        rgb += shadeValue & 0x600000;
        j8 = shadeValue >> 23;
    }
    for (int l3 = end_x - start_x & 7; l3-- > 0;) {
        int j9;
        if ((j9 = texture[(loops & 0x3f80) + (rgb >> 7)] >>> j8) != 0 && true) {
            dest[dest_off] = j9;
            DrawingArea.depthBuffer[dest_off] = depth;
        }
        depth += depth_slope;
        dest_off++;
        rgb += j7;
        loops += l7;
    }
}

public static void drawDepthTriangle(int x_a, int x_b, int x_c, int y_a, int y_b, int y_c, float z_a, float z_b, float z_c) {
        int a_to_b = 0;
        if (y_b != y_a) {
                a_to_b = (x_b - x_a << 16) / (y_b - y_a);
        }
        int b_to_c = 0;
        if (y_c != y_b) {
                b_to_c = (x_c - x_b << 16) / (y_c - y_b);
        }
        int c_to_a = 0;
        if (y_c != y_a) {
                c_to_a = (x_a - x_c << 16) / (y_a - y_c);
        }

        float b_aX = x_b - x_a;
        float b_aY = y_b - y_a;
        float c_aX = x_c - x_a;
        float c_aY = y_c - y_a;
        float b_aZ = z_b - z_a;
        float c_aZ = z_c - z_a;

        float div = b_aX * c_aY - c_aX * b_aY;
        float depth_slope = (b_aZ * c_aY - c_aZ * b_aY) / div;
        float depth_increment = (c_aZ * b_aX - b_aZ * c_aX) / div;
        if (y_a <= y_b && y_a <= y_c) {
                if (y_a < DrawingArea.bottomY) {
                        if (y_b > DrawingArea.bottomY)
                                y_b = DrawingArea.bottomY;
                        if (y_c > DrawingArea.bottomY)
                                y_c = DrawingArea.bottomY;
                        z_a = z_a - depth_slope * x_a + depth_slope;
                        if (y_b < y_c) {
                                x_c = x_a <<= 16;
                                if (y_a < 0) {
                                        x_c -= c_to_a * y_a;
                                        x_a -= a_to_b * y_a;
                                        z_a -= depth_increment * y_a;
                                        y_a = 0;
                                }
                                x_b <<= 16;
                                if (y_b < 0) {
                                        x_b -= b_to_c * y_b;
                                        y_b = 0;
                                }
                                if (y_a != y_b && c_to_a < a_to_b || y_a == y_b && c_to_a > b_to_c) {
                                        y_c -= y_b;
                                        y_b -= y_a;
                                        y_a = anIntArray1472[y_a];
                                        while (--y_b >= 0) {
                                                drawDepthTriangleScanline(y_a, x_c >> 16, x_a >> 16, z_a, depth_slope);
                                                x_c += c_to_a;
                                                x_a += a_to_b;
                                                z_a += depth_increment;
                                                y_a += DrawingArea.width;
                                        }
                                        while (--y_c >= 0) {
                                                drawDepthTriangleScanline(y_a, x_c >> 16, x_b >> 16, z_a, depth_slope);
                                                x_c += c_to_a;
                                                x_b += b_to_c;
                                                z_a += depth_increment;
                                                y_a += DrawingArea.width;
                                        }
                                } else {
                                        y_c -= y_b;
                                        y_b -= y_a;
                                        y_a = anIntArray1472[y_a];
                                        while (--y_b >= 0) {
                                                drawDepthTriangleScanline(y_a, x_a >> 16, x_c >> 16, z_a, depth_slope);
                                                x_c += c_to_a;
                                                x_a += a_to_b;
                                                z_a += depth_increment;
                                                y_a += DrawingArea.width;
                                        }
                                        while (--y_c >= 0) {
                                                drawDepthTriangleScanline(y_a, x_b >> 16, x_c >> 16, z_a, depth_slope);
                                                x_c += c_to_a;
                                                x_b += b_to_c;
                                                z_a += depth_increment;
                                                y_a += DrawingArea.width;
                                        }
                                }
                        } else {
                                x_b = x_a <<= 16;
                                if (y_a < 0) {
                                        x_b -= c_to_a * y_a;
                                        x_a -= a_to_b * y_a;
                                        z_a -= depth_increment * y_a;
                                        y_a = 0;
                                }
                                x_c <<= 16;
                                if (y_c < 0) {
                                        x_c -= b_to_c * y_c;
                                        y_c = 0;
                                }
                                if (y_a != y_c && c_to_a < a_to_b || y_a == y_c && b_to_c > a_to_b) {
                                        y_b -= y_c;
                                        y_c -= y_a;
                                        y_a = anIntArray1472[y_a];
                                        while (--y_c >= 0) {
                                                drawDepthTriangleScanline(y_a, x_b >> 16, x_a >> 16, z_a, depth_slope);
                                                x_b += c_to_a;
                                                x_a += a_to_b;
                                                z_a += depth_increment;
                                                y_a += DrawingArea.width;
                                        }
                                        while (--y_b >= 0) {
                                                drawDepthTriangleScanline(y_a, x_c >> 16, x_a >> 16, z_a, depth_slope);
                                                x_c += b_to_c;
                                                x_a += a_to_b;
                                                z_a += depth_increment;
                                                y_a += DrawingArea.width;
                                        }
                                } else {
                                        y_b -= y_c;
                                        y_c -= y_a;
                                        y_a = anIntArray1472[y_a];
                                        while (--y_c >= 0) {
                                                drawDepthTriangleScanline(y_a, x_a >> 16, x_b >> 16, z_a, depth_slope);
                                                x_b += c_to_a;
                                                x_a += a_to_b;
                                                z_a += depth_increment;
                                                y_a += DrawingArea.width;
                                        }
                                        while (--y_b >= 0) {
                                                drawDepthTriangleScanline(y_a, x_a >> 16, x_c >> 16, z_a, depth_slope);
                                                x_c += b_to_c;
                                                x_a += a_to_b;
                                                z_a += depth_increment;
                                                y_a += DrawingArea.width;
                                        }
                                }
                        }
                }
        } else if (y_b <= y_c) {
                if (y_b < DrawingArea.bottomY) {
                        if (y_c > DrawingArea.bottomY)
                                y_c = DrawingArea.bottomY;
                        if (y_a > DrawingArea.bottomY)
                                y_a = DrawingArea.bottomY;
                        z_b = z_b - depth_slope * x_b + depth_slope;
                        if (y_c < y_a) {
                                x_a = x_b <<= 16;
                                if (y_b < 0) {
                                        x_a -= a_to_b * y_b;
                                        x_b -= b_to_c * y_b;
                                        z_b -= depth_increment * y_b;
                                        y_b = 0;
                                }
                                x_c <<= 16;
                                if (y_c < 0) {
                                        x_c -= c_to_a * y_c;
                                        y_c = 0;
                                }
                                if (y_b != y_c && a_to_b < b_to_c || y_b == y_c && a_to_b > c_to_a) {
                                        y_a -= y_c;
                                        y_c -= y_b;
                                        y_b = anIntArray1472[y_b];
                                        while (--y_c >= 0) {
                                                drawDepthTriangleScanline(y_b, x_a >> 16, x_b >> 16, z_b, depth_slope);
                                                x_a += a_to_b;
                                                x_b += b_to_c;
                                                z_b += depth_increment;
                                                y_b += DrawingArea.width;
                                        }
                                        while (--y_a >= 0) {
                                                drawDepthTriangleScanline(y_b, x_a >> 16, x_c >> 16, z_b, depth_slope);
                                                x_a += a_to_b;
                                                x_c += c_to_a;
                                                z_b += depth_increment;
                                                y_b += DrawingArea.width;
                                        }
                                } else {
                                        y_a -= y_c;
                                        y_c -= y_b;
                                        y_b = anIntArray1472[y_b];
                                        while (--y_c >= 0) {
                                                drawDepthTriangleScanline(y_b, x_b >> 16, x_a >> 16, z_b, depth_slope);
                                                x_a += a_to_b;
                                                x_b += b_to_c;
                                                z_b += depth_increment;
                                                y_b += DrawingArea.width;
                                        }
                                        while (--y_a >= 0) {
                                                drawDepthTriangleScanline(y_b, x_c >> 16, x_a >> 16, z_b, depth_slope);
                                                x_a += a_to_b;
                                                x_c += c_to_a;
                                                z_b += depth_increment;
                                                y_b += DrawingArea.width;
                                        }
                                }
                        } else {
                                x_c = x_b <<= 16;
                                if (y_b < 0) {
                                        x_c -= a_to_b * y_b;
                                        x_b -= b_to_c * y_b;
                                        z_b -= depth_increment * y_b;
                                        y_b = 0;
                                }
                                x_a <<= 16;
                                if (y_a < 0) {
                                        x_a -= c_to_a * y_a;
                                        y_a = 0;
                                }
                                if (a_to_b < b_to_c) {
                                        y_c -= y_a;
                                        y_a -= y_b;
                                        y_b = anIntArray1472[y_b];
                                        while (--y_a >= 0) {
                                                drawDepthTriangleScanline(y_b, x_c >> 16, x_b >> 16, z_b, depth_slope);
                                                x_c += a_to_b;
                                                x_b += b_to_c;
                                                z_b += depth_increment;
                                                y_b += DrawingArea.width;
                                        }
                                        while (--y_c >= 0) {
                                                drawDepthTriangleScanline(y_b, x_a >> 16, x_b >> 16, z_b, depth_slope);
                                                x_a += c_to_a;
                                                x_b += b_to_c;
                                                z_b += depth_increment;
                                                y_b += DrawingArea.width;
                                        }
                                } else {
                                        y_c -= y_a;
                                        y_a -= y_b;
                                        y_b = anIntArray1472[y_b];
                                        while (--y_a >= 0) {
                                                drawDepthTriangleScanline(y_b, x_b >> 16, x_c >> 16, z_b, depth_slope);
                                                x_c += a_to_b;
                                                x_b += b_to_c;
                                                z_b += depth_increment;
                                                y_b += DrawingArea.width;
                                        }
                                        while (--y_c >= 0) {
                                                drawDepthTriangleScanline(y_b, x_b >> 16, x_a >> 16, z_b, depth_slope);
                                                x_a += c_to_a;
                                                x_b += b_to_c;
                                                z_b += depth_increment;
                                                y_b += DrawingArea.width;
                                        }
                                }
                        }
                }
        } else if (y_c < DrawingArea.bottomY) {
                if (y_a > DrawingArea.bottomY)
                        y_a = DrawingArea.bottomY;
                if (y_b > DrawingArea.bottomY)
                        y_b = DrawingArea.bottomY;
                z_c = z_c - depth_slope * x_c + depth_slope;
                if (y_a < y_b) {
                        x_b = x_c <<= 16;
                        if (y_c < 0) {
                                x_b -= b_to_c * y_c;
                                x_c -= c_to_a * y_c;
                                z_c -= depth_increment * y_c;
                                y_c = 0;
                        }
                        x_a <<= 16;
                        if (y_a < 0) {
                                x_a -= a_to_b * y_a;
                                y_a = 0;
                        }
                        if (b_to_c < c_to_a) {
                                y_b -= y_a;
                                y_a -= y_c;
                                y_c = anIntArray1472[y_c];
                                while (--y_a >= 0) {
                                        drawDepthTriangleScanline(y_c, x_b >> 16, x_c >> 16, z_c, depth_slope);
                                        x_b += b_to_c;
                                        x_c += c_to_a;
                                        z_c += depth_increment;
                                        y_c += DrawingArea.width;
                                }
                                while (--y_b >= 0) {
                                        drawDepthTriangleScanline(y_c, x_b >> 16, x_a >> 16, z_c, depth_slope);
                                        x_b += b_to_c;
                                        x_a += a_to_b;
                                        z_c += depth_increment;
                                        y_c += DrawingArea.width;
                                }
                        } else {
                                y_b -= y_a;
                                y_a -= y_c;
                                y_c = anIntArray1472[y_c];
                                while (--y_a >= 0) {
                                        drawDepthTriangleScanline(y_c, x_c >> 16, x_b >> 16, z_c, depth_slope);
                                        x_b += b_to_c;
                                        x_c += c_to_a;
                                        z_c += depth_increment;
                                        y_c += DrawingArea.width;
                                }
                                while (--y_b >= 0) {
                                        drawDepthTriangleScanline(y_c, x_a >> 16, x_b >> 16, z_c, depth_slope);
                                        x_b += b_to_c;
                                        x_a += a_to_b;
                                        z_c += depth_increment;
                                        y_c += DrawingArea.width;
                                }
                        }
                } else {
                        x_a = x_c <<= 16;
                        if (y_c < 0) {
                                x_a -= b_to_c * y_c;
                                x_c -= c_to_a * y_c;
                                z_c -= depth_increment * y_c;
                                y_c = 0;
                        }
                        x_b <<= 16;
                        if (y_b < 0) {
                                x_b -= a_to_b * y_b;
                                y_b = 0;
                        }
                        if (b_to_c < c_to_a) {
                                y_a -= y_b;
                                y_b -= y_c;
                                y_c = anIntArray1472[y_c];
                                while (--y_b >= 0) {
                                        drawDepthTriangleScanline(y_c, x_a >> 16, x_c >> 16, z_c, depth_slope);
                                        x_a += b_to_c;
                                        x_c += c_to_a;
                                        z_c += depth_increment;
                                        y_c += DrawingArea.width;
                                }
                                while (--y_a >= 0) {
                                        drawDepthTriangleScanline(y_c, x_b >> 16, x_c >> 16, z_c, depth_slope);
                                        x_b += a_to_b;
                                        x_c += c_to_a;
                                        z_c += depth_increment;
                                        y_c += DrawingArea.width;
                                }
                        } else {
                                y_a -= y_b;
                                y_b -= y_c;
                                y_c = anIntArray1472[y_c];
                                while (--y_b >= 0) {
                                        drawDepthTriangleScanline(y_c, x_c >> 16, x_a >> 16, z_c, depth_slope);
                                        x_a += b_to_c;
                                        x_c += c_to_a;
                                        z_c += depth_increment;
                                        y_c += DrawingArea.width;
                                }
                                while (--y_a >= 0) {
                                        drawDepthTriangleScanline(y_c, x_c >> 16, x_b >> 16, z_c, depth_slope);
                                        x_b += a_to_b;
                                        x_c += c_to_a;
                                        z_c += depth_increment;
                                        y_c += DrawingArea.width;
                                }
                        }
                }
        }
}

private static void drawDepthTriangleScanline(int dest_off, int start_x, int end_x, float depth, float depth_slope) {
        int dbl = DrawingArea.depthBuffer.length;
        if (aBoolean1462) {
                if (end_x > DrawingArea.width) {
                        end_x = DrawingArea.width;
                }
                if (start_x < 0) {
                        start_x = 0;
                }
        }
        if (start_x >= end_x) {
                return;
        }
        dest_off += start_x - 1;
        int loops = end_x - start_x >> 2;
        depth += depth_slope * (float) start_x;
        if (anInt1465 == 0) {
                while (--loops >= 0) {
                        dest_off++;
                        if (dest_off >= 0 && dest_off < dbl && true) {
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        depth += depth_slope;
                        dest_off++;
                        if (dest_off >= 0 && dest_off < dbl && true) {
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        depth += depth_slope;
                        dest_off++;
                        if (dest_off >= 0 && dest_off < dbl && true) {
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        depth += depth_slope;
                        dest_off++;
                        if (dest_off >= 0 && dest_off < dbl && true) {
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        depth += depth_slope;
                }
                for (loops = end_x - start_x & 3; --loops >= 0;) {
                        dest_off++;
                        if (dest_off >= 0 && dest_off < dbl && true) {
                                DrawingArea.depthBuffer[dest_off] = depth;
                        }
                        depth += depth_slope;
                }
                return;
        }
        while (--loops >= 0) {
                dest_off++;
                if (dest_off >= 0 && dest_off < dbl && true) {
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
                depth += depth_slope;
                dest_off++;
                if (dest_off >= 0 && dest_off < dbl && true) {
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
                depth += depth_slope;
                dest_off++;
                if (dest_off >= 0 && dest_off < dbl && true) {
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
                depth += depth_slope;
                dest_off++;
                if (dest_off >= 0 && dest_off < dbl && true) {
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
                depth += depth_slope;
        }
        for (loops = end_x - start_x & 3; --loops >= 0;) {
                dest_off++;
                if (dest_off >= 0 && dest_off < dbl && true) {
                        DrawingArea.depthBuffer[dest_off] = depth;
                }
                depth += depth_slope;
        }
}

	public static void method378(int i, int j, int k, int l, int i1, int j1, int k1, int l1, 
			int i2, int j2, int k2, int l2, int i3, int j3, int k3, 
			int l3, int i4, int j4, int k4)
	{
		int ai[] = method371(k4);
		aBoolean1463 = !aBooleanArray1475[k4];
		k2 = j2 - k2;
		j3 = i3 - j3;
		i4 = l3 - i4;
		l2 -= j2;
		k3 -= i3;
		j4 -= l3;
		int l4 = l2 * i3 - k3 * j2 << 14;
		int i5 = k3 * l3 - j4 * i3 << 8;
		int j5 = j4 * j2 - l2 * l3 << 5;
		int k5 = k2 * i3 - j3 * j2 << 14;
		int l5 = j3 * l3 - i4 * i3 << 8;
		int i6 = i4 * j2 - k2 * l3 << 5;
		int j6 = j3 * l2 - k2 * k3 << 14;
		int k6 = i4 * k3 - j3 * j4 << 8;
		int l6 = k2 * j4 - i4 * l2 << 5;
		int i7 = 0;
		int j7 = 0;
		if(j != i)
		{
			i7 = (i1 - l << 16) / (j - i);
			j7 = (l1 - k1 << 16) / (j - i);
		}
		int k7 = 0;
		int l7 = 0;
		if(k != j)
		{
			k7 = (j1 - i1 << 16) / (k - j);
			l7 = (i2 - l1 << 16) / (k - j);
		}
		int i8 = 0;
		int j8 = 0;
		if(k != i)
		{
			i8 = (l - j1 << 16) / (i - k);
			j8 = (k1 - i2 << 16) / (i - k);
		}
		if(i <= j && i <= k)
		{
			if(i >= DrawingArea.bottomY)
				return;
			if(j > DrawingArea.bottomY)
				j = DrawingArea.bottomY;
			if(k > DrawingArea.bottomY)
				k = DrawingArea.bottomY;
			if(j < k)
			{
				j1 = l <<= 16;
				i2 = k1 <<= 16;
				if(i < 0)
				{
					j1 -= i8 * i;
					l -= i7 * i;
					i2 -= j8 * i;
					k1 -= j7 * i;
					i = 0;
				}
				i1 <<= 16;
				l1 <<= 16;
				if(j < 0)
				{
					i1 -= k7 * j;
					l1 -= l7 * j;
					j = 0;
				}
				int k8 = i - textureInt2;
				l4 += j5 * k8;
				k5 += i6 * k8;
				j6 += l6 * k8;
				if(i != j && i8 < i7 || i == j && i8 > k7)
				{
					k -= j;
					j -= i;
					i = anIntArray1472[i];
					while(--j >= 0) 
					{
						method379(DrawingArea.pixels, ai, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
						j1 += i8;
						l += i7;
						i2 += j8;
						k1 += j7;
						i += DrawingArea.width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					while(--k >= 0) 
					{
						method379(DrawingArea.pixels, ai, i, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
						j1 += i8;
						i1 += k7;
						i2 += j8;
						l1 += l7;
						i += DrawingArea.width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					return;
				}
				k -= j;
				j -= i;
				i = anIntArray1472[i];
				while(--j >= 0) 
				{
					method379(DrawingArea.pixels, ai, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
					j1 += i8;
					l += i7;
					i2 += j8;
					k1 += j7;
					i += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--k >= 0) 
				{
					method379(DrawingArea.pixels, ai, i, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
					j1 += i8;
					i1 += k7;
					i2 += j8;
					l1 += l7;
					i += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			i1 = l <<= 16;
			l1 = k1 <<= 16;
			if(i < 0)
			{
				i1 -= i8 * i;
				l -= i7 * i;
				l1 -= j8 * i;
				k1 -= j7 * i;
				i = 0;
			}
			j1 <<= 16;
			i2 <<= 16;
			if(k < 0)
			{
				j1 -= k7 * k;
				i2 -= l7 * k;
				k = 0;
			}
			int l8 = i - textureInt2;
			l4 += j5 * l8;
			k5 += i6 * l8;
			j6 += l6 * l8;
			if(i != k && i8 < i7 || i == k && k7 > i7)
			{
				j -= k;
				k -= i;
				i = anIntArray1472[i];
				while(--k >= 0) 
				{
					method379(DrawingArea.pixels, ai, i, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					i1 += i8;
					l += i7;
					l1 += j8;
					k1 += j7;
					i += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--j >= 0) 
				{
					method379(DrawingArea.pixels, ai, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					j1 += k7;
					l += i7;
					i2 += l7;
					k1 += j7;
					i += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			j -= k;
			k -= i;
			i = anIntArray1472[i];
			while(--k >= 0) 
			{
				method379(DrawingArea.pixels, ai, i, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
				i1 += i8;
				l += i7;
				l1 += j8;
				k1 += j7;
				i += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while(--j >= 0) 
			{
				method379(DrawingArea.pixels, ai, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
				j1 += k7;
				l += i7;
				i2 += l7;
				k1 += j7;
				i += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		if(j <= k)
		{
			if(j >= DrawingArea.bottomY)
				return;
			if(k > DrawingArea.bottomY)
				k = DrawingArea.bottomY;
			if(i > DrawingArea.bottomY)
				i = DrawingArea.bottomY;
			if(k < i)
			{
				l = i1 <<= 16;
				k1 = l1 <<= 16;
				if(j < 0)
				{
					l -= i7 * j;
					i1 -= k7 * j;
					k1 -= j7 * j;
					l1 -= l7 * j;
					j = 0;
				}
				j1 <<= 16;
				i2 <<= 16;
				if(k < 0)
				{
					j1 -= i8 * k;
					i2 -= j8 * k;
					k = 0;
				}
				int i9 = j - textureInt2;
				l4 += j5 * i9;
				k5 += i6 * i9;
				j6 += l6 * i9;
				if(j != k && i7 < k7 || j == k && i7 > i8)
				{
					i -= k;
					k -= j;
					j = anIntArray1472[j];
					while(--k >= 0) 
					{
						method379(DrawingArea.pixels, ai, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
						l += i7;
						i1 += k7;
						k1 += j7;
						l1 += l7;
						j += DrawingArea.width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					while(--i >= 0) 
					{
						method379(DrawingArea.pixels, ai, j, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
						l += i7;
						j1 += i8;
						k1 += j7;
						i2 += j8;
						j += DrawingArea.width;
						l4 += j5;
						k5 += i6;
						j6 += l6;
					}
					return;
				}
				i -= k;
				k -= j;
				j = anIntArray1472[j];
				while(--k >= 0) 
				{
					method379(DrawingArea.pixels, ai, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					l += i7;
					i1 += k7;
					k1 += j7;
					l1 += l7;
					j += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--i >= 0) 
				{
					method379(DrawingArea.pixels, ai, j, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					l += i7;
					j1 += i8;
					k1 += j7;
					i2 += j8;
					j += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			j1 = i1 <<= 16;
			i2 = l1 <<= 16;
			if(j < 0)
			{
				j1 -= i7 * j;
				i1 -= k7 * j;
				i2 -= j7 * j;
				l1 -= l7 * j;
				j = 0;
			}
			l <<= 16;
			k1 <<= 16;
			if(i < 0)
			{
				l -= i8 * i;
				k1 -= j8 * i;
				i = 0;
			}
			int j9 = j - textureInt2;
			l4 += j5 * j9;
			k5 += i6 * j9;
			j6 += l6 * j9;
			if(i7 < k7)
			{
				k -= i;
				i -= j;
				j = anIntArray1472[j];
				while(--i >= 0) 
				{
					method379(DrawingArea.pixels, ai, j, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
					j1 += i7;
					i1 += k7;
					i2 += j7;
					l1 += l7;
					j += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--k >= 0) 
				{
					method379(DrawingArea.pixels, ai, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
					l += i8;
					i1 += k7;
					k1 += j8;
					l1 += l7;
					j += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			k -= i;
			i -= j;
			j = anIntArray1472[j];
			while(--i >= 0) 
			{
				method379(DrawingArea.pixels, ai, j, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
				j1 += i7;
				i1 += k7;
				i2 += j7;
				l1 += l7;
				j += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while(--k >= 0) 
			{
				method379(DrawingArea.pixels, ai, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
				l += i8;
				i1 += k7;
				k1 += j8;
				l1 += l7;
				j += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		if(k >= DrawingArea.bottomY)
			return;
		if(i > DrawingArea.bottomY)
			i = DrawingArea.bottomY;
		if(j > DrawingArea.bottomY)
			j = DrawingArea.bottomY;
		if(i < j)
		{
			i1 = j1 <<= 16;
			l1 = i2 <<= 16;
			if(k < 0)
			{
				i1 -= k7 * k;
				j1 -= i8 * k;
				l1 -= l7 * k;
				i2 -= j8 * k;
				k = 0;
			}
			l <<= 16;
			k1 <<= 16;
			if(i < 0)
			{
				l -= i7 * i;
				k1 -= j7 * i;
				i = 0;
			}
			int k9 = k - textureInt2;
			l4 += j5 * k9;
			k5 += i6 * k9;
			j6 += l6 * k9;
			if(k7 < i8)
			{
				j -= i;
				i -= k;
				k = anIntArray1472[k];
				while(--i >= 0) 
				{
					method379(DrawingArea.pixels, ai, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
					i1 += k7;
					j1 += i8;
					l1 += l7;
					i2 += j8;
					k += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				while(--j >= 0) 
				{
					method379(DrawingArea.pixels, ai, k, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
					i1 += k7;
					l += i7;
					l1 += l7;
					k1 += j7;
					k += DrawingArea.width;
					l4 += j5;
					k5 += i6;
					j6 += l6;
				}
				return;
			}
			j -= i;
			i -= k;
			k = anIntArray1472[k];
			while(--i >= 0) 
			{
				method379(DrawingArea.pixels, ai, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
				i1 += k7;
				j1 += i8;
				l1 += l7;
				i2 += j8;
				k += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while(--j >= 0) 
			{
				method379(DrawingArea.pixels, ai, k, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
				i1 += k7;
				l += i7;
				l1 += l7;
				k1 += j7;
				k += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		l = j1 <<= 16;
		k1 = i2 <<= 16;
		if(k < 0)
		{
			l -= k7 * k;
			j1 -= i8 * k;
			k1 -= l7 * k;
			i2 -= j8 * k;
			k = 0;
		}
		i1 <<= 16;
		l1 <<= 16;
		if(j < 0)
		{
			i1 -= i7 * j;
			l1 -= j7 * j;
			j = 0;
		}
		int l9 = k - textureInt2;
		l4 += j5 * l9;
		k5 += i6 * l9;
		j6 += l6 * l9;
		if(k7 < i8)
		{
			i -= j;
			j -= k;
			k = anIntArray1472[k];
			while(--j >= 0) 
			{
				method379(DrawingArea.pixels, ai, k, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
				l += k7;
				j1 += i8;
				k1 += l7;
				i2 += j8;
				k += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			while(--i >= 0) 
			{
				method379(DrawingArea.pixels, ai, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
				i1 += i7;
				j1 += i8;
				l1 += j7;
				i2 += j8;
				k += DrawingArea.width;
				l4 += j5;
				k5 += i6;
				j6 += l6;
			}
			return;
		}
		i -= j;
		j -= k;
		k = anIntArray1472[k];
		while(--j >= 0) 
		{
			method379(DrawingArea.pixels, ai, k, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
			l += k7;
			j1 += i8;
			k1 += l7;
			i2 += j8;
			k += DrawingArea.width;
			l4 += j5;
			k5 += i6;
			j6 += l6;
		}
		while(--i >= 0) 
		{
			method379(DrawingArea.pixels, ai, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
			i1 += i7;
			j1 += i8;
			l1 += j7;
			i2 += j8;
			k += DrawingArea.width;
			l4 += j5;
			k5 += i6;
			j6 += l6;
		}
	}

	private static void method379(int ai[], int ai1[], int k, int l, int i1, int j1,
								  int k1, int l1, int i2, int j2, int k2, int l2, int i3)
	{
		int i = 0;//was parameter
		int j = 0;//was parameter
		if(l >= i1)
			return;
		int j3;
		int k3;
		if(aBoolean1462)
		{
			j3 = (k1 - j1) / (i1 - l);
			if(i1 > DrawingArea.centerX)
				i1 = DrawingArea.centerX;
			if(l < 0)
			{
				j1 -= l * j3;
				l = 0;
			}
			if(l >= i1)
				return;
			k3 = i1 - l >> 3;
			j3 <<= 12;
			j1 <<= 9;
		} else
		{
			if(i1 - l > 7)
			{
				k3 = i1 - l >> 3;
				j3 = (k1 - j1) * anIntArray1468[k3] >> 6;
			} else
			{
				k3 = 0;
				j3 = 0;
			}
			j1 <<= 9;
		}
		k += l;
		if(lowMem)
		{
			int i4 = 0;
			int k4 = 0;
			int k6 = l - textureInt1;
			l1 += (k2 >> 3) * k6;
			i2 += (l2 >> 3) * k6;
			j2 += (i3 >> 3) * k6;
			int i5 = j2 >> 12;
			if(i5 != 0)
			{
				i = l1 / i5;
				j = i2 / i5;
				if(i < 0)
					i = 0;
				else
				if(i > 4032)
					i = 4032;
			}
			l1 += k2;
			i2 += l2;
			j2 += i3;
			i5 = j2 >> 12;
			if(i5 != 0)
			{
				i4 = l1 / i5;
				k4 = i2 / i5;
				if(i4 < 7)
					i4 = 7;
				else
				if(i4 > 4032)
					i4 = 4032;
			}
			int i7 = i4 - i >> 3;
			int k7 = k4 - j >> 3;
			i += (j1 & 0x600000) >> 3;
			int i8 = j1 >> 23;
			if(aBoolean1463)
			{
				while(k3-- > 0) 
				{
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i = i4;
					j = k4;
					l1 += k2;
					i2 += l2;
					j2 += i3;
					int j5 = j2 >> 12;
					if(j5 != 0)
					{
						i4 = l1 / j5;
						k4 = i2 / j5;
						if(i4 < 7)
							i4 = 7;
						else
						if(i4 > 4032)
							i4 = 4032;
					}
					i7 = i4 - i >> 3;
					k7 = k4 - j >> 3;
					j1 += j3;
					i += (j1 & 0x600000) >> 3;
					i8 = j1 >> 23;
				}
				for(k3 = i1 - l & 7; k3-- > 0;)
				{
					ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
					i += i7;
					j += k7;
				}

				return;
			}
			while(k3-- > 0) 
			{
				int k8;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = k8;
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = k8;
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = k8;
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = k8;
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = k8;
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = k8;
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = k8;
				k++;
				i += i7;
				j += k7;
				if((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = k8;
				k++;
				i = i4;
				j = k4;
				l1 += k2;
				i2 += l2;
				j2 += i3;
				int k5 = j2 >> 12;
				if(k5 != 0)
				{
					i4 = l1 / k5;
					k4 = i2 / k5;
					if(i4 < 7)
						i4 = 7;
					else
					if(i4 > 4032)
						i4 = 4032;
				}
				i7 = i4 - i >> 3;
				k7 = k4 - j >> 3;
				j1 += j3;
				i += (j1 & 0x600000) >> 3;
				i8 = j1 >> 23;
			}
			for(k3 = i1 - l & 7; k3-- > 0;)
			{
				int l8;
				if((l8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0)
					ai[k] = l8;
				k++;
				i += i7;
				j += k7;
			}

			return;
		}
		int j4 = 0;
		int l4 = 0;
		int l6 = l - textureInt1;
		l1 += (k2 >> 3) * l6;
		i2 += (l2 >> 3) * l6;
		j2 += (i3 >> 3) * l6;
		int l5 = j2 >> 14;
		if(l5 != 0)
		{
			i = l1 / l5;
			j = i2 / l5;
			if(i < 0)
				i = 0;
			else
			if(i > 16256)
				i = 16256;
		}
		l1 += k2;
		i2 += l2;
		j2 += i3;
		l5 = j2 >> 14;
		if(l5 != 0)
		{
			j4 = l1 / l5;
			l4 = i2 / l5;
			if(j4 < 7)
				j4 = 7;
			else
			if(j4 > 16256)
				j4 = 16256;
		}
		int j7 = j4 - i >> 3;
		int l7 = l4 - j >> 3;
		i += j1 & 0x600000;
		int j8 = j1 >> 23;
		if(aBoolean1463)
		{
			while(k3-- > 0) 
			{
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i = j4;
				j = l4;
				l1 += k2;
				i2 += l2;
				j2 += i3;
				int i6 = j2 >> 14;
				if(i6 != 0)
				{
					j4 = l1 / i6;
					l4 = i2 / i6;
					if(j4 < 7)
						j4 = 7;
					else
					if(j4 > 16256)
						j4 = 16256;
				}
				j7 = j4 - i >> 3;
				l7 = l4 - j >> 3;
				j1 += j3;
				i += j1 & 0x600000;
				j8 = j1 >> 23;
			}
			for(k3 = i1 - l & 7; k3-- > 0;)
			{
				ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
				i += j7;
				j += l7;
			}

			return;
		}
		while(k3-- > 0) 
		{
			int i9;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = i9;
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = i9;
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = i9;
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = i9;
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = i9;
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = i9;
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = i9;
			k++;
			i += j7;
			j += l7;
			if((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = i9;
			k++;
			i = j4;
			j = l4;
			l1 += k2;
			i2 += l2;
			j2 += i3;
			int j6 = j2 >> 14;
			if(j6 != 0)
			{
				j4 = l1 / j6;
				l4 = i2 / j6;
				if(j4 < 7)
					j4 = 7;
				else
				if(j4 > 16256)
					j4 = 16256;
			}
			j7 = j4 - i >> 3;
			l7 = l4 - j >> 3;
			j1 += j3;
			i += j1 & 0x600000;
			j8 = j1 >> 23;
		}
		for(int l3 = i1 - l & 7; l3-- > 0;)
		{
			int j9;
			if((j9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0)
				ai[k] = j9;
			k++;
			i += j7;
			j += l7;
		}

	}

	public static final int anInt1459 = -477;
	public static boolean lowMem = true;
	static boolean aBoolean1462;
	private static boolean aBoolean1463;
	public static boolean aBoolean1464 = true;
	public static int anInt1465;
	public static int textureInt1;
	public static int textureInt2;
	private static int[] anIntArray1468;
	public static final int[] anIntArray1469;
	public static int anIntArray1470[];
	public static int anIntArray1471[];
	public static int anIntArray1472[];
	private static int anInt1473;
	public static Background aBackgroundArray1474s[] = new Background[50];
	private static boolean[] aBooleanArray1475 = new boolean[50];
	private static int[] anIntArray1476 = new int[50];
	private static int anInt1477;
	private static int[][] anIntArrayArray1478;
	private static int[][] anIntArrayArray1479 = new int[50][];
	public static int anIntArray1480[] = new int[50];
	public static int anInt1481;
	public static int anIntArray1482[] = new int[0x10000];
	private static int[][] anIntArrayArray1483 = new int[50][];

	static 
	{
		anIntArray1468 = new int[512];
		anIntArray1469 = new int[2048];
		anIntArray1470 = new int[2048];
		anIntArray1471 = new int[2048];
		for(int i = 1; i < 512; i++)
			anIntArray1468[i] = 32768 / i;

		for(int j = 1; j < 2048; j++)
			anIntArray1469[j] = 0x10000 / j;

		for(int k = 0; k < 2048; k++)
		{
			anIntArray1470[k] = (int)(65536D * Math.sin((double)k * 0.0030679614999999999D));
			anIntArray1471[k] = (int)(65536D * Math.cos((double)k * 0.0030679614999999999D));
		}

	}
}
