package com.example.lenovo.myapplication.MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.lenovo.myapplication.R;

/**
 * Created by lenovo on 2017/2/15.
 */

public class SongProgressbar extends View {

    private static final int STYLE_HORIZONTAL = 0;
    private static final int STYLE_ROUND = 1;
    private static final int STYLE_SECTOR=2;
    /**进度背景画笔**/
//	private Paint mBgpaint;
    /**进度画笔**/
//	private Paint mPrgpaint;
    /**进度文字画笔**/
//	private Paint mTextpaint;
    /**
     * 圆形进度条边框宽度
     **/
    private int strokeWidth=20;
    /**
     * 进度条中心X坐标
     **/
    private int centerX;
    /**
     * 进度条中心Y坐标
     **/
    private int centerY;
    /**
     * 进度提示文字大小
     **/
    private int percenttextsize = 18;
    /**
     * 进度提示文字颜色
     **/
    private int percenttextcolor = 0xff009ACD;
    /**
     * 进度条背景颜色
     **/
    private int progressBarBgColor = 0xff636363;
    /**
     * 进度颜色
     **/
    private int progressColor = 0xff00C5CD;
    /**
     * 扇形扫描进度的颜色
     */
    private int sectorColor=0xaaffffff;
    /**
     * 扇形扫描背景
     */
    private int unSweepColor = 0xaa5e5e5e;
    /**
     * 进度条样式（水平/圆形）
     **/
    private int orientation = STYLE_HORIZONTAL;
    /**
     * 圆形进度条半径
     **/
    private int radius = 30;
    /**
     * 进度最大值
     **/
    private int max = 100;
    /**
     * 进度值
     **/
    private int progress = 0;
    /**
     * 水平进度条是否是空心
     **/
    private boolean isHorizonStroke;
    /**
     * 水平进度圆角值
     **/
    private int rectRound=5;
    /**进度文字是否显示百分号**/
    private boolean showPercentSign;
    private Paint mPaint;
    public static String percent;


    public SongProgressbar(Context context) {
        this(context, null);
    }

    public SongProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SongProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SongProgressbar);
        percenttextcolor = array.getColor(R.styleable.SongProgressbar_percent_text_color, percenttextcolor);
        progressBarBgColor = array.getColor(R.styleable.SongProgressbar_progressBarBgColor, progressBarBgColor);
        progressColor = array.getColor(R.styleable.SongProgressbar_progressColor, progressColor);
        sectorColor = array.getColor(R.styleable.SongProgressbar_sectorColor, sectorColor);
        unSweepColor = array.getColor(R.styleable.SongProgressbar_unSweepColor, unSweepColor);
        percenttextsize = (int) array.getDimension(R.styleable.SongProgressbar_percent_text_size, percenttextsize);
        strokeWidth = (int) array.getDimension(R.styleable.SongProgressbar_stroke_width, strokeWidth);
        rectRound = (int) array.getDimension(R.styleable.SongProgressbar_rect_round, rectRound);
        orientation = array.getInteger(R.styleable.SongProgressbar_orientation, STYLE_HORIZONTAL);
        isHorizonStroke = array.getBoolean(R.styleable.SongProgressbar_isHorizonStroke, false);
        showPercentSign = array.getBoolean(R.styleable.SongProgressbar_showPercentSign, true);
//		mBgpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		mPrgpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		mTextpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        array.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = centerX - strokeWidth / 2;

        drawRoundProgressBar(canvas, mPaint);

    }

    /**
     * 绘制圆形进度条
     *
     * @param canvas
     */
    private void drawRoundProgressBar(Canvas canvas, Paint paint) {
        // 初始化画笔属性
        paint.setColor(progressBarBgColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        // 画圆
        canvas.drawCircle(centerX, centerY, radius, paint);
        // 画圆形进度
        paint.setColor(progressColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        RectF oval = new RectF(centerX - radius, centerY - radius, radius + centerX, radius + centerY);
        canvas.drawArc(oval, -90, 360 * progress / max, false, paint);
        // 画进度文字
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(percenttextcolor);
        paint.setTextSize(percenttextsize);

        percent = (int) (progress * 100 / max) + "%";
        Rect rect = new Rect();
        paint.getTextBounds(percent, 0, percent.length(), rect);
        float textWidth = rect.width();
        float textHeight = rect.height();
        if (textWidth >= radius * 2) {
            textWidth = radius * 2;
        }
        Paint.FontMetrics metrics = paint.getFontMetrics();
        float baseline = (getMeasuredHeight()-metrics.bottom+metrics.top)/2-metrics.top;

        /**
         * 不画出进度文字
         */
//        canvas.drawText(percent, centerX - textWidth / 2, baseline, paint);

    }

    public void setSchedule(int progress) {
        if (progress > max) {
            progress = max;
        } else {
            this.progress = progress;
            postInvalidate();
        }
    }

    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 真正的绘图逻辑
     *
     * @param canvas
     * @param piant
     */
//    @Override
//    protected void render(Canvas canvas, Paint piant) {
//
//    }
}
