package com.demo6.shop.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDTO {
  private  String fullname;
  private String comment;
LocalDateTime timecomment;
private Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(fullname, that.fullname) && Objects.equals(comment, that.comment) && Objects.equals(timecomment, that.timecomment) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, comment, timecomment, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimecomment() {
        return timecomment;
    }

    public void setTimecomment(LocalDateTime timecomment) {
        this.timecomment = timecomment;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
