package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MyStocks", schema = "deepView", catalog = "")
public class MyStocksEntity {
    private int id;
    private Integer userId;
    private Integer secuId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserID", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "SecuID", nullable = true)
    public Integer getSecuId() {
        return secuId;
    }

    public void setSecuId(Integer secuId) {
        this.secuId = secuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyStocksEntity that = (MyStocksEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(secuId, that.secuId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, secuId);
    }
}
