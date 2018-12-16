package com.example.heoju.smartdv;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class BitmapRunnable implements Runnable {
    protected ImageView logo;
    protected String imageUrl;

    public BitmapRunnable(ImageView ivBitmap, String imageUrl) {
        //그림그려줄 레퍼런스를 보내줘야하니까..
        this.logo = ivBitmap;
        this.imageUrl = imageUrl;
    }

    @Override
    public void run() {
        //비트맵을 가져올수 있다. 팩토리. 디코드 스트림.인풋스트림으로 변화시켜준다음에 흐름을 모아서 비트맵으로 만들어줘야한다. 이를 비트맵팩토리의 디코드스트림메소드 이다.
        try {
            final Bitmap bitmap = BitmapFactory.decodeStream((InputStream) (new URL(imageUrl).getContent()));
            //ivBitmap.setImageBitmap(bitmap); 버튼 클릭시 에러가 난다. 쓰레드 영역을 구분하지 않아 침범했다.
            logo.post(new Runnable() {
                @Override
                public void run() { //여기서 실행되는 것이 아니다, 실행할 수 있는 코드를 Runnabled에 담아 보낸다.
                    logo.setImageBitmap(bitmap);
                }
            }); //메세지를 그냥 보낼뿐이다 수신 여부는 모른다.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
