package entites;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user_package", schema = "homework02", catalog = "")
public class UserPackageEntity {
    private int id;
    private String username;
    private Date creationDate;
    private int pid;
    private byte onEffect;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 60)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "creation_date", nullable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "pid", nullable = false)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "on_effect", nullable = false)
    public byte getOnEffect() {
        return onEffect;
    }

    public void setOnEffect(byte onEffect) {
        this.onEffect = onEffect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPackageEntity that = (UserPackageEntity) o;

        if (id != that.id) return false;
        if (pid != that.pid) return false;
        if (onEffect != that.onEffect) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + pid;
        result = 31 * result + (int) onEffect;
        return result;
    }
}
