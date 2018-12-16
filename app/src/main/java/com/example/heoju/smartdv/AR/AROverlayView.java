package com.example.heoju.smartdv.AR;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.opengl.Matrix;
import android.view.View;

//AR관련 class import
import com.example.heoju.smartdv.AR.helper.LocationHelper;
import com.example.heoju.smartdv.AR.model.ARPoint;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ntdat on 1/13/17.
 */

public class AROverlayView extends View {

    Context context;
    private float[] rotatedProjectionMatrix = new float[16];
    private Location currentLocation;
    private List<ARPoint> arPoints;


    public AROverlayView(Context context) {
        super(context);

        this.context = context;

        //preView에 표현할 AR Object의 위도 latitude, 경도 longitude, 고도 altitude 값을 각각 입력
        arPoints = new ArrayList<ARPoint>() {{

            /*
             *  학교 시설
             */
            add(new ARPoint("M 본관 & 중앙도서관", 36.32588131527357, 127.3386447960461, 79.9 + 23.4));
            add(new ARPoint("N 학생회관", 36.32814496514787, 127.33921949762619, 73.2 + 23.4));

            /*
             *  단과대학 입력
             */
            add(new ARPoint("E 사회과학대학", 36.32602263516994, 127.33981992270677, 78.9 + 23.4));
            add(new ARPoint("A 신학대학", 36.32603937440869, 127.33752398724797, 81.6 + 23.4));
            add(new ARPoint("G 미술대학", 36.32347749985266, 127.33775846830328, 85.8 + 23.4));
            add(new ARPoint("U 사범대학", 36.32855256118745, 127.34063923503996, 69.4 + 23.4));
            add(new ARPoint("B 인문대학", 36.32733436325447, 127.33932426133093, 85.3 + 23.4));
            add(new ARPoint("F 음악대학", 36.32727568838344, 127.33981728409613, 77.0 + 23.4));
            add(new ARPoint("C 테크노과학대학", 36.32285573051969, 127.33797700563575, 86.6 + 23.4));
            add(new ARPoint("D 공과대학", 36.321546144293805, 127.33824152430185, 127.88923645019531));

        }};


    }

    public void updateRotatedProjectionMatrix(float[] rotatedProjectionMatrix) {
        this.rotatedProjectionMatrix = rotatedProjectionMatrix;
        this.invalidate();
    }

    public void updateCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        this.invalidate();
    }


    //ARPoint 각 객체들을 그리는 onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (currentLocation == null) {
            return;
        }

        final int radius = 30;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(60);

        for (int i = 0; i < arPoints.size(); i++) {
            float[] currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation);
            float[] pointInECEF = LocationHelper.WSG84toECEF(arPoints.get(i).getLocation());
            float[] pointInENU = LocationHelper.ECEFtoENU(currentLocation, currentLocationInECEF, pointInECEF);

            float[] cameraCoordinateVector = new float[4];
            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0);

            // 서페이스뷰에 그려질 O모양 원을 그린다. O위에 각 건물 이름을 띄운다.
            if (cameraCoordinateVector[2] < 0) {
                float x = (0.5f + cameraCoordinateVector[0] / cameraCoordinateVector[3]) * canvas.getWidth();
                float y = (0.5f - cameraCoordinateVector[1] / cameraCoordinateVector[3]) * canvas.getHeight();

                //실제로 오브젝트를 그려준다.
                canvas.drawCircle(x, y, radius, paint);
                canvas.drawText(arPoints.get(i).getName(), x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);
            }
        }


    }


}
