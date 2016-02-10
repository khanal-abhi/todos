package co.khanal.todos;

/**
 * Created by abhi on 2/10/16.
 */
public class ThingToDo {
    private String title;
    private boolean completed;
    private long id;

    public ThingToDo(String title) {
        this.title = title;
        this.completed = false;
        this.id = 0;
    }

    public ThingToDo(long id, String title, boolean completed){
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString(){
        return String.format("_id:%d, title:%s, completed:%b", id, title, completed);
    }
}