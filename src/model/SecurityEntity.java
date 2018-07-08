package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Security", schema = "deepView", catalog = "")
public class SecurityEntity {
    private int id;
    private String secuCode;
    private String secuAbbr;
    private String chiSpelling;
    private Date listedDate;
    private Date endDate;
    private Integer indId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SecuCode", nullable = true, length = 11)
    public String getSecuCode() {
        return secuCode;
    }

    public void setSecuCode(String secuCode) {
        this.secuCode = secuCode;
    }

    @Basic
    @Column(name = "SecuAbbr", nullable = true, length = 16)
    public String getSecuAbbr() {
        return secuAbbr;
    }

    public void setSecuAbbr(String secuAbbr) {
        this.secuAbbr = secuAbbr;
    }

    @Basic
    @Column(name = "ChiSpelling", nullable = true, length = 5)
    public String getChiSpelling() {
        return chiSpelling;
    }

    public void setChiSpelling(String chiSpelling) {
        this.chiSpelling = chiSpelling;
    }

    @Basic
    @Column(name = "ListedDate", nullable = true)
    public Date getListedDate() {
        return listedDate;
    }

    public void setListedDate(Date listedDate) {
        this.listedDate = listedDate;
    }

    @Basic
    @Column(name = "EndDate", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "IndID", nullable = true)
    public Integer getIndId() {
        return indId;
    }

    public void setIndId(Integer indId) {
        this.indId = indId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityEntity that = (SecurityEntity) o;
        return id == that.id &&
                Objects.equals(secuCode, that.secuCode) &&
                Objects.equals(secuAbbr, that.secuAbbr) &&
                Objects.equals(chiSpelling, that.chiSpelling) &&
                Objects.equals(listedDate, that.listedDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(indId, that.indId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, secuCode, secuAbbr, chiSpelling, listedDate, endDate, indId);
    }
}
