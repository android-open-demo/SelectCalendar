package sing.select.calendar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout


class SquareLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private var backType = 0
    private var backColor = Color.BLUE
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    /**
     * 绘制背景
     * @param type:0圆，-1，左半圆，-2右半圆，-3圆，-4矩形
     */
    fun setBackGround(type: Int = 0, color: Int = Color.parseColor("#E2E8EE")) {
        backType = type
        backColor = color
        paint.color = backColor
        postInvalidate()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        drawBackGround(canvas)
        super.dispatchDraw(canvas)
    }

    private fun drawBackGround(canvas: Canvas?) {
        val radius = height / 2f
        canvas?.apply {
            drawColor(Color.TRANSPARENT)
            when (backType) {
                -1 -> { // 第一个
                    // 底下浅绿色
                    val path = Path()
                    path.moveTo(radius, radius)
                    val rids = floatArrayOf(20F, 20F, 0F, 0F, 0F, 0F, 20F, 20F)
                    path.addRoundRect(RectF(20f, 20f, radius * 2, radius * 2 - 20f),rids,Path.Direction.CW)
                    path.close()
                    paint.color = Color.parseColor("#e3ffef")
                    drawPath(path, paint)

                    // 深绿色
                    paint.color = Color.parseColor("#2ebc48")
                    drawPath(getButtonPath(radius), paint)
                }
                -2 -> {// 最后一个
                    val path = Path()
                    path.moveTo(radius, radius)
                    val rids = floatArrayOf(0F, 0F, 20F, 20F, 20F, 20F, 0F, 0F)
                    path.addRoundRect(RectF(0f, 20f, radius * 2 - 20F, radius * 2 - 20f),rids,Path.Direction.CW)
                    path.close()
                    paint.color = Color.parseColor("#e3ffef")
                    drawPath(path, paint)


                    paint.color = Color.parseColor("#2ebc48")
                    drawPath(getButtonPath(radius), paint)
                }
                -3 -> { // 只按下一个的
                    paint.color = Color.parseColor("#2ebc48")
                    drawPath(getButtonPath(radius), paint)
                }
                -4 -> { // 第一个和最后一个都按下后中间的那部分
                    val path = Path()
                    path.moveTo(radius, radius)
                    val rids = floatArrayOf(0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F)
                    path.addRoundRect(RectF(0f, 20f, radius * 2, radius * 2-20f),rids,Path.Direction.CW)
                    path.close()
                    paint.color = Color.parseColor("#e3ffef")
                    drawPath(path, paint)
                }
            }
        }
    }

    private fun getButtonPath(radius: Float): Path {
        val path = Path()
        path.moveTo(radius, radius)
        val rids = floatArrayOf(20F, 20F, 20F, 20F, 20F, 20F, 20F, 20F)
        path.addRoundRect(RectF(15f, 15f, radius * 2-15, radius * 2-15f),rids,Path.Direction.CW)
        path.close()
        return path
    }
}