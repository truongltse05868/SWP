/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Lesson {
    private int lesson_id;
    private int subject_id;
    private String title;
    private boolean status;

    public Lesson() {
    }

    public Lesson(int lesson_id, int subject_id, String title, boolean status) {
        this.lesson_id = lesson_id;
        this.subject_id = subject_id;
        this.title = title;
        this.status = status;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lesson{" + "lesson_id=" + lesson_id + ", subject_id=" + subject_id + ", title=" + title + ", status=" + status + '}';
    }
    
}
