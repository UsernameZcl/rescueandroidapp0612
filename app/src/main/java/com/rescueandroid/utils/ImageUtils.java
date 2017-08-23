package com.rescueandroid.utils;

import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * ͼƬ������
 * @author zhb
 * 2011-05-10
 */
public class ImageUtils {

	/**
	 * ����λͼ�ļ�
	 * zhb 2012-01-07
	 * @param path
	 * @param bitmap
	 * @param width
	 * @param height
	 * @param quality
	 * @return
	 */
	public static boolean saveJPEGImage(String path, Bitmap bitmap, int width, int height, int quality)
	{
		File file = new File(path);
		Bitmap bm = bitmap;
		BufferedOutputStream bos = null;
		try {
			if(!file.exists())
				file.createNewFile();
			bos = new BufferedOutputStream(  
					new FileOutputStream(file));
			if(width>0 && height>0)
			{
				bm = Bitmap.createScaledBitmap(bm, width, height, false); 
			}
			bm.compress(Bitmap.CompressFormat.JPEG, quality, bos);  
			bos.flush();  
			bos.close(); 
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	/**
	 * λͼ����
	 * @param bitmap
	 * @param dst_w
	 * @param dst_h
	 * @return
	 */
	public static Bitmap scaleBitmap(Bitmap bitmap, int dst_w, int dst_h)
	{
		int  src_w = bitmap.getWidth();
		int  src_h = bitmap.getHeight();
		float scale_w = ((float)dst_w)/src_w;
		float  scale_h = ((float)dst_h)/src_h;
		Matrix  matrix = new Matrix();
		matrix.postScale(scale_w, scale_h);
		Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix, true);
		return dstbmp;
	}
	
	public static Bitmap copyBitmap(Bitmap bitmap)
	{
		int  src_w = bitmap.getWidth();
		int  src_h = bitmap.getHeight();
		Matrix  matrix = new Matrix();
		Bitmap dstbmp = Bitmap.createBitmap(bitmap, 0, 0, src_w, src_h, matrix, true);
		return dstbmp;
	}
	/**
	 * Drawable����
	 * @param drawable
	 * @param dst_w
	 * @param dst_h
	 * @return
	 */
	public static Drawable scaleDrawable(Drawable drawable, int dst_w, int dst_h)
	{
		Bitmap bm = ImageUtils.convertBitmapToDrawable(drawable);
		bm = ImageUtils.scaleBitmap(bm, dst_w, dst_h);
		return ImageUtils.convertDrawableToBitmap(bm);
	}
	
	public static Bitmap scaleDrawableToBitmap(Drawable drawable, int dst_w, int dst_h)
	{
		Bitmap bm = ImageUtils.convertBitmapToDrawable(drawable);
		Bitmap bitmap = ImageUtils.scaleBitmap(bm, dst_w, dst_h);
		bm.recycle();
		return bitmap;
	}
	
	/**
	 * λͼ��ת
	 * zhb 2012-01-07
	 * @param src
	 * @param angle
	 * @return
	 */
	public static Bitmap rotaingBitmap(Bitmap src, int angle) {  
		int width = src.getWidth();
		int height = src.getHeight();
		Matrix matrix = new Matrix();  
        //��תͼƬ ����  
        matrix.postRotate(angle);  
        // �����µ�ͼƬ  
        Bitmap resizedBitmap = Bitmap.createBitmap(src, 0, 0,  
        		width, height, matrix, false);   
        return resizedBitmap;  
    }
	
	/**
	 * �ӱ���Ŀ¼����λͼ
	 * zhb 2012-01-07
	 * @param context
	 * @param dir
	 * @return
	 */
	public static Bitmap loadBitmap(String dir)
	{
		InputStream in = null;
		try {
			//in = context.getAssets().open(dir);
			in = new FileInputStream(dir);
			Bitmap defaultIcon = BitmapFactory.decodeStream(in); 
			return defaultIcon;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * DrawableתBitmap
	 * @param drawable
	 * @return
	 */
	public static Bitmap convertBitmapToDrawable(Drawable drawable)
	{
//		int width = drawable.getIntrinsicWidth();
//		int height = drawable.getIntrinsicWidth();		  
//		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ?
//				Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
//		Canvas canvas = new Canvas(bitmap);
//		drawable.setBounds(0, 0, width, height); 
//		drawable.draw(canvas);
//		return bitmap;
		BitmapDrawable bd = (BitmapDrawable)drawable;
		Bitmap bm = bd.getBitmap();
		return bm;
	}
	
	/**
	 * BitmapתDrawable
	 * @param bitmap
	 * @return
	 */
	public static Drawable convertDrawableToBitmap(Bitmap bitmap)
	{
		Drawable drawable = new BitmapDrawable(bitmap); 
		return drawable;
	}
	
	// ���Բ��ͼƬ�ķ���
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
		
	// ��ô���Ӱ��ͼƬ����
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight()
				+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);

		return bitmapWithReflection;
	}

	/**
	 * ͼƬ�ҶȻ�
	 * @param bmpOriginal
	 * @return
	 */
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
		int width, height;
		height = bmpOriginal.getHeight();
		width = bmpOriginal.getWidth();
 
		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint(); 
		ColorMatrix cm = new ColorMatrix(); 
		cm.setSaturation(0); // ��ɫ
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpOriginal, 0, 0, paint);
		return bmpGrayscale;
	}
	
    /** 
     * ����Ч��
     * @param bmp 
     * @return 
     */  
	public static Bitmap effectOldRemeber(Bitmap bmp)  
    {  
        // �ٶȲ���  
        long start = System.currentTimeMillis();  
        int width = bmp.getWidth();  
        int height = bmp.getHeight();  
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
        int pixColor = 0;  
        int pixR = 0;  
        int pixG = 0;  
        int pixB = 0;  
        int newR = 0;  
        int newG = 0;  
        int newB = 0;  
        int[] pixels = new int[width * height];  
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);  
        for (int i = 0; i < height; i++)  
        {  
            for (int k = 0; k < width; k++)  
            {  
                pixColor = pixels[width * i + k];  
                pixR = Color.red(pixColor);  
                pixG = Color.green(pixColor);  
                pixB = Color.blue(pixColor);  
                newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);  
                newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);  
                newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);  
                int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);  
                pixels[width * i + k] = newColor;  
            }  
        }  
          
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);  
        long end = System.currentTimeMillis();  
        Log.d("may", "used time="+(end - start));  
        return bitmap;  
    }  
	
	/**
     * �ữЧ��(��˹ģ��)(�Ż�������������)
     * @param bmp
     * @return
     */ 
	public static Bitmap effectBlurImageAmeliorate(Bitmap bmp) 
    { 
        long start = System.currentTimeMillis(); 
        // ��˹���� 
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 }; 
         
        int width = bmp.getWidth(); 
        int height = bmp.getHeight(); 
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.RGB_565);
         
        int pixR = 0; 
        int pixG = 0; 
        int pixB = 0; 
         
        int pixColor = 0; 
         
        int newR = 0; 
        int newG = 0; 
        int newB = 0; 
         
        int delta = 16; // ֵԽСͼƬ��Խ����Խ����Խ�� 
         
        int idx = 0; 
        int[] pixels = new int[width * height]; 
        bmp.getPixels(pixels, 0, width, 0, 0, width, height); 
        for (int i = 1, length = height - 1; i < length; i++) 
        { 
            for (int k = 1, len = width - 1; k < len; k++) 
            { 
                idx = 0; 
                for (int m = -1; m <= 1; m++) 
                { 
                    for (int n = -1; n <= 1; n++) 
                    { 
                        pixColor = pixels[(i + m) * width + k + n]; 
                        pixR = Color.red(pixColor); 
                        pixG = Color.green(pixColor); 
                        pixB = Color.blue(pixColor); 
                         
                        newR = newR + (int) (pixR * gauss[idx]); 
                        newG = newG + (int) (pixG * gauss[idx]); 
                        newB = newB + (int) (pixB * gauss[idx]); 
                        idx++; 
                    } 
                } 
                 
                newR /= delta; 
                newG /= delta; 
                newB /= delta; 
                 
                newR = Math.min(255, Math.max(0, newR)); 
                newG = Math.min(255, Math.max(0, newG)); 
                newB = Math.min(255, Math.max(0, newB)); 
                 
                pixels[i * width + k] = Color.argb(255, newR, newG, newB); 
                 
                newR = 0; 
                newG = 0; 
                newB = 0; 
            } 
        } 
         
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height); 
        long end = System.currentTimeMillis(); 
        Log.d("may", "used time="+(end - start)); 
        return bitmap; 
    }  
	
	//
	//����
	//
	public static Bitmap effectIce(Bitmap bmp) 
	{
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        int dst[] = new int[width * height];
        bmp.getPixels(dst, 0, width, 0, 0, width, height);

        int R, G, B, pixel;
        int pos, pixColor;
        for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                        pos = y * width + x;
                        pixColor = dst[pos]; // ��ȡͼƬ��ǰ�������ֵ

                        R = Color.red(pixColor); // ��ȡRGB��ԭɫ
                        G = Color.green(pixColor);
                        B = Color.blue(pixColor);

                        pixel = R - G - B;
                        pixel = pixel * 3 / 2;
                        if (pixel < 0)
                                pixel = -pixel;
                        if (pixel > 255)
                                pixel = 255;
                        R = pixel; // ���������Rֵ��������ͬ

                        pixel = G - B - R;
                        pixel = pixel * 3 / 2;
                        if (pixel < 0)
                                pixel = -pixel;
                        if (pixel > 255)
                                pixel = 255;
                        G = pixel;

                        pixel = B - R - G;
                        pixel = pixel * 3 / 2;
                        if (pixel < 0)
                                pixel = -pixel;
                        if (pixel > 255)
                                pixel = 255;
                        B = pixel;
                        dst[pos] = Color.rgb(R, G, B); // ���õ�ǰ�������ֵ
                } // x
        } // y
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.setPixels(dst, 0, width, 0, 0, width, height);
        return bitmap;
	} // end of Ice
	
	
	/**
	 * ����
	 */
	public static Bitmap effectProcess(Bitmap bmp) 
	{
		int width = bmp.getWidth();
	    int height = bmp.getHeight();
	
	    int dst[] = new int[width * height];
	    int R, G, B, pixel;
	    int pos, pixColor;
	    bmp.getPixels(dst, 0, width, 0, 0, width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	pos = y * width + x;
                pixColor = dst[pos]; // ��ȡͼƬ��ǰ�������ֵ
            	R = Color.red(pixColor); // ��ȡRGB��ԭɫ
                            G = Color.green(pixColor);
                            B = Color.blue(pixColor);
 
                                pixel = R * 128 / (G + B + 1);
                                if (pixel < 0)
                                        pixel = 0;
                                if (pixel > 255)
                                        pixel = 255;
                                R = pixel;
 
                                pixel = G * 128 / (B + R + 1);
                                if (pixel < 0)
                                        pixel = 0;
                                if (pixel > 255)
                                        pixel = 255;
                                G = pixel;
 
                                pixel = B * 128 / (R + G + 1);
                                if (pixel < 0)
                                        pixel = 0;
                                if (pixel > 255)
                                        pixel = 255;
                                B = pixel;
                                dst[pos] = Color.rgb(R, G, B); // ���õ�ǰ�������ֵ
            } // x
        } // y
	         
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bitmap.setPixels(dst, 0, width, 0, 0, width, height);
        return bitmap;
	}
        
        
       
    /**
     * �����Աȶ���Ч
     */
    public static Bitmap effectBrightContrast(Bitmap bmp)  {
    	 float BrightnessFactor = 0.25f;
    	 
        /**
         * The contrast factor. Should be in the range [-1, 1]
         */
         float ContrastFactor = 0f;
        
    	int width = bmp.getWidth();
        int height = bmp.getHeight();
        int dst[] = new int[width * height];
        int r, g, b, pixel;
        int pos, pixColor;
        bmp.getPixels(dst, 0, width, 0, 0, width, height);
            // Convert to integer factors
            int bfi = (int) (BrightnessFactor * 255);
            float cf = 1f + ContrastFactor;
            cf *= cf;
            int cfi = (int) (cf * 32768) + 1;
            for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                    	pos = y * width + x;
                        pixColor = dst[pos]; // ��ȡͼƬ��ǰ�������ֵ
                    	r = Color.red(pixColor); // ��ȡRGB��ԭɫ
                        g = Color.green(pixColor);
                        b = Color.blue(pixColor);
                            // Modify brightness (addition)
                            if (bfi != 0) {
                                    // Add brightness
                                    int ri = r + bfi;
                                    int gi = g + bfi;
                                    int bi = b + bfi;
                                    // Clamp to byte boundaries
                                    r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
                                    g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
                                    b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);
                            }
                            // Modifiy contrast (multiplication)
                            if (cfi != 32769) {
                                    // Transform to range [-128, 127]
                                        int ri = r - 128;
                                        int gi = g - 128;
                                        int bi = b - 128;
 
                                        // Multiply contrast factor
                                        ri = (ri * cfi) >> 15;
                                        gi = (gi * cfi) >> 15;
                                        bi = (bi * cfi) >> 15;
 
                                        // Transform back to range [0, 255]
                                        ri = ri + 128;
                                        gi = gi + 128;
                                        bi = bi + 128;
 
                                        // Clamp to byte boundaries
                                    r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
                                    g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
                                    b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);
                            }
                            //image.setPixelColor(x, y, r, g, b);
                            dst[pos] = Color.rgb(r, g, b); // ���õ�ǰ�������ֵ
                    }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            bitmap.setPixels(dst, 0, width, 0, 0, width, height);
            return bitmap;
    }
         
	/**
	 * ��Ч��
	 * @author ��ɪBoy
	 *
	 */
    public static Bitmap effectFeather(Bitmap bmp) {
		float Size = 0.5f;
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		int dst[] = new int[width * height];
		int pixel;
		int pos, pixColor;
		int ratio = width > height ? height * 32768 / width : width * 32768 / height;
		int cx = width >> 1;
        int cy = height >> 1;
        int max = cx * cx + cy * cy;
        int min = (int) (max * (1 - Size));
        int diff = max - min;
		 
        int R, G, B;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	pos = y * width + x;
                pixColor = dst[pos]; // ��ȡͼƬ��ǰ�������ֵ
				R = Color.red(pixColor); // ��ȡRGB��ԭɫ
		        G = Color.green(pixColor);
		        B = Color.blue(pixColor);
		        // Calculate distance to center and adapt aspect ratio
		        int dx = cx - x;
		        int dy = cy - y;
		        if (width > height) {
		                dx = (dx * ratio) >> 15;
		        } else {
		                dy = (dy * ratio) >> 15;
		        }
		        int distSq = dx * dx + dy * dy;
		        float v = ((float) distSq / diff) * 255;
		        R = (int) (R + (v));
		        G = (int) (G + (v));
		        B = (int) (B + (v));
		        R = (R > 255 ? 255 : (R < 0 ? 0 : R));
		        G = (G > 255 ? 255 : (G < 0 ? 0 : G));
		        B = (B > 255 ? 255 : (B < 0 ? 0 : B));
		        dst[pos] = Color.rgb(R, G, B); // ���õ�ǰ�������ֵ
			}
        }
	    Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    bitmap.setPixels(dst, 0, width, 0, 0, width, height);
	    return bitmap;
    }
           
    
    /**
	 * ��ȡ��ԴͼƬ
	 * 
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// ��ȡ��ԴͼƬ
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}
	
	public static Bitmap readAssestBitMap(Context context, String name) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		//opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// ��ȡ��ԴͼƬ
		InputStream is;
		try {
			is = context.getAssets().open(name);
			return BitmapFactory.decodeStream(is, null, opt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
