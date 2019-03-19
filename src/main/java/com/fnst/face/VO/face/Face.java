package com.fnst.face.VO.face;

import java.util.List;

/**
 * @author Luyue
 * @date 2019/3/18 16:18
 **/
public class Face {

    private List<FaceRectangleVO> faces1;
    private List<FaceRectangleVO> faces2;
    private Integer time_used;
    private Thresholds thresholds;
    private Double confidence;
    private String image_id2;
    private String image_id1;
    private String request_id;

    public List<FaceRectangleVO> getFaces1() {
        return faces1;
    }

    public void setFaces1(List<FaceRectangleVO> faces1) {
        this.faces1 = faces1;
    }

    public List<FaceRectangleVO> getFaces2() {
        return faces2;
    }

    public void setFaces2(List<FaceRectangleVO> faces2) {
        this.faces2 = faces2;
    }

    public Integer getTime_used() {
        return time_used;
    }

    public void setTime_used(Integer time_used) {
        this.time_used = time_used;
    }

    public Thresholds getThresholds() {
        return thresholds;
    }

    public void setThresholds(Thresholds thresholds) {
        this.thresholds = thresholds;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getImage_id2() {
        return image_id2;
    }

    public void setImage_id2(String image_id2) {
        this.image_id2 = image_id2;
    }

    public String getImage_id1() {
        return image_id1;
    }

    public void setImage_id1(String image_id1) {
        this.image_id1 = image_id1;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
}
