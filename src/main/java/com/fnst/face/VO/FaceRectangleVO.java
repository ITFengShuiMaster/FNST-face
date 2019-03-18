package com.fnst.face.VO;

/**
 * @author Luyue
 * @date 2019/3/18 16:20
 **/
public class FaceRectangleVO {
    private FaceRectangle face_rectangle;
    private String face_token;

    public FaceRectangle getFace_rectangle() {
        return face_rectangle;
    }

    public void setFace_rectangle(FaceRectangle face_rectangle) {
        this.face_rectangle = face_rectangle;
    }

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }
}
