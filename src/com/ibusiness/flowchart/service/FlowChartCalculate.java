package com.ibusiness.flowchart.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

/**
 * 在线画流程图 公式计算
 * 
 * @author JiangBo
 * 
 */
public class FlowChartCalculate {

    /**
     * 单例模式
     */
    private static FlowChartCalculate instance = new FlowChartCalculate();

    private FlowChartCalculate() {
    }

    public static FlowChartCalculate getInstance() {
        return instance;
    }

    /**
     * 计算画线的开始坐标
     * 
     * @return
     */
    public String calculateStartEndPos(JSONObject imgStart, JSONObject imgEnd) {
        List<Integer> realPoints = new ArrayList<Integer>();
        int sx = imgStart.getInt("x");
        int sy = imgStart.getInt("y");
        int swidth = imgStart.getInt("width");
        int sheight = imgStart.getInt("height");
        int ex = imgEnd.getInt("x");
        int ey = imgEnd.getInt("y");
        int ewidth = imgEnd.getInt("width");
        int eheight = imgEnd.getInt("height");
        // 计算连线开始和结束坐标的位置
        List<Integer> array = calculateXY(sy, sheight, ey, eheight, sx, swidth, ex, ewidth);
        List<Integer> crossArray = null;
        if ((array.get(0) != array.get(2)) && (array.get(1) != array.get(3))) {
            // 计算折叠点的位置
            crossArray = calculateCrossPoints(array, sy, sheight, ey, eheight, sx, swidth, ex, ewidth);
        }
        realPoints.add(array.get(0));
        realPoints.add(array.get(1));
        if (crossArray != null)
            for (int i = 0; i < crossArray.size(); i++) {
                realPoints.add(crossArray.get(i));
            }
        realPoints.add(array.get(2));
        realPoints.add(array.get(3));
        String realPointStr = realPoints.toString();
        realPointStr = realPointStr.replace(" ", "");
        realPointStr = realPointStr.replace("[", "");
        realPointStr = realPointStr.replace("]", "");
        return realPointStr;
    }

    /**
     * 计算连线开始和结束坐标的位置
     */
    private List<Integer> calculateXY(int sy, int sheight, int ey, int eheight, int sx, int swidth, int ex, int ewidth) {
        List<Integer> array = new ArrayList<Integer>();
        if (sy == ey) {
            if (sx > ex) {
                array.add(sx);
                array.add(sy + (sheight / 2));
                array.add(ex + ewidth);
                array.add(sy + (eheight / 2));
            } else {
                array.add(sx + swidth);
                array.add(sy + (sheight / 2));
                array.add(ex);
                array.add(sy + (eheight / 2));
            }
        } else {
            if (sy > ey && (sy - ey > sheight)) {
                if (sx > ex) {
                    if (sx - ex < swidth / 2) {
                        array.add(sx);
                        array.add(sy + (sheight / 2));
                        array.add(ex);
                        array.add(ey + eheight / 2);
                    } else {
                        array.add(sx);
                        array.add(sy + (sheight / 2));
                        array.add(ex + (ewidth / 2));
                        array.add(ey + eheight);
                    }
                } else if (sx == ex) {
                    array.add(sx + (swidth / 2));
                    array.add(sy);
                    array.add(ex + (ewidth / 2));
                    array.add(ey + eheight);
                } else {
                    if (ex - sx < ewidth / 2) {
                        array.add(sx);
                        array.add(sy + (sheight / 2));
                        array.add(ex);
                        array.add(ey + eheight / 2);
                    } else {
                        array.add(sx + swidth);
                        array.add(sy + (sheight / 2));
                        array.add(ex + (ewidth / 2));
                        array.add(ey + eheight);
                    }
                }
            } else if (sy < ey && (ey - sy > eheight)) {
                if (sx > ex) {
                    if (sx - ex < swidth / 2) {
                        array.add(sx);
                        array.add(sy + sheight / 2);
                        array.add(ex);
                        array.add(ey + eheight / 2);
                    } else {
                        array.add(sx + (swidth / 2));
                        array.add(sy + sheight);
                        array.add(ex + ewidth);
                        array.add(ey + (eheight / 2));
                    }

                } else if (sx == ex) {
                    array.add(sx + (swidth / 2));
                    array.add(sy + sheight);
                    array.add(ex + (ewidth / 2));
                    array.add(ey);
                } else {
                    if (ex - sx < ewidth / 2) {
                        array.add(sx);
                        array.add(sy + sheight / 2);
                        array.add(ex);
                        array.add(ey + eheight / 2);
                    } else {
                        array.add(sx + (swidth / 2));
                        array.add(sy + sheight);
                        array.add(ex);
                        array.add(ey + (eheight / 2));
                    }
                }
            } else {
                if ((sy < ey && (ey - sy < eheight)) || sy > ey && (sy - ey < sheight)) {
                    array.add(sx + swidth / 2);
                    array.add(sy);
                    array.add(ex + ewidth / 2);
                    array.add(ey);
                }
            }
        }
        return array;
    }

    /**
     * 计算折叠点的位置
     */
    private List<Integer> calculateCrossPoints(List<Integer> array, int sy, int sheight, int ey, int eheight, int sx,
            int swidth, int ex, int ewidth) {
        List<Integer> pointsArray = new ArrayList<Integer>();
        int x = array.get(0);
        int y = array.get(1);
        int x1 = array.get(2);
        int y1 = array.get(3);
        Boolean f = false;
        if ((x - x1) > 0 && (y - y1) > 0) {
            f = true;
        }
        if ((x1 - x) > 0 && (y1 - y) > 0) {
            f = true;
        }
        /**
         * y轴的多个折叠点
         */
        if ((sy > ey && sy - ey <= sheight) || (sy < ey && ey - sy <= eheight)) {
            if (sy > ey && sy - ey <= sheight) {
                pointsArray.add(x);
                pointsArray.add(y1 - sheight);
                pointsArray.add(x1);
                pointsArray.add(y1 - sheight);
            } else if (sy < ey && ey - sy <= eheight) {
                pointsArray.add(x);
                pointsArray.add(y - eheight);
                pointsArray.add(x1);
                pointsArray.add(y - eheight);
            }
        } else if ((sx > ex && sx - ex <= sheight / 2) || (sx < ex && ex - sx <= eheight / 2)) {
            /**
             * x轴的多个折点
             */
            // x= sx-swidth/2
            // y=y+sheight/2

            if (sx - ex < sheight / 2) {
                pointsArray.add(sx - ewidth);
                pointsArray.add(sy + sheight / 2);
                pointsArray.add(sx - ewidth);
                pointsArray.add(ey + eheight / 2);
            } else if (ex - sx < eheight / 2) {
                pointsArray.add(ex - swidth);
                pointsArray.add(sy + sheight / 2);
                pointsArray.add(ex - swidth);
                pointsArray.add(ey + eheight / 2);
            }
        } else {
            /**
             * 单个折叠点
             */
            if (f) {
                if (x < x1) {
                    pointsArray.add(x);
                } else {
                    pointsArray.add(x1);
                }
                if (y < y1) {
                    pointsArray.add(y1);
                } else {
                    pointsArray.add(y);
                }
            } else {
                if (x < x1) {
                    pointsArray.add(x1);
                } else {
                    pointsArray.add(x);
                }
                if (y < y1) {
                    pointsArray.add(y1);
                } else {
                    pointsArray.add(y);
                }
            }
        }
        return pointsArray;
    }

}
