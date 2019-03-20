package com.fnst.face.VO.face;

/**
 * @author Luyue
 * @date 2019/3/19 22:20
 **/
public class FaceOne {

    private String face_token;
    private Object face_rectangle;
    private Object landmark;
    private Object attributes;

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    public Object getFace_rectangle() {
        return face_rectangle;
    }

    public void setFace_rectangle(Object face_rectangle) {
        this.face_rectangle = face_rectangle;
    }

    public Object getLandmark() {
        return landmark;
    }

    public void setLandmark(Object landmark) {
        this.landmark = landmark;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }
}
