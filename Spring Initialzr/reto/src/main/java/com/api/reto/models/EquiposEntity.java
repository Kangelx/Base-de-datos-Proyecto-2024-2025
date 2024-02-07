package com.api.reto.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "equipos", schema = "tic_incidencias", catalog = "")
public class EquiposEntity {
<<<<<<< HEAD
   /* public enum TipoEquipoEnum {
=======
    public enum TipoEquipoEnum {
>>>>>>> ed18ab7994a95d3e9d80eb4f272c384927257e39
        altavoces,
        impresora,
        monitor,
        pantalla_interactiva,
        portátil_de_aula,
        portátil_Consejería,
        proyector
<<<<<<< HEAD
    }*/

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Transient
  /*  @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_equipo", nullable = false)*/
    private Byte tipoEquipo;
=======
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "tipo_equipo", nullable = false)
    private TipoEquipoEnum tipoEquipo;
>>>>>>> ed18ab7994a95d3e9d80eb4f272c384927257e39
    @Basic
    @Column(name = "fecha_adquisicion", nullable = true)
    private Date fechaAdquisicion;
    @Basic
    @Column(name = "etiqueta", nullable = false, length = 8)
    private String etiqueta;
    @Basic
    @Column(name = "marca", nullable = false, length = 20)
    private String marca;
    @Basic
    @Column(name = "modelo", nullable = true, length = 45)
    private String modelo;
    @Basic
    @Column(name = "descripcion", nullable = true, length = -1)
    private String descripcion;
    @Basic

    @Column(name = "baja", nullable = true)
    private Byte baja;
    @ManyToOne
    @JoinColumn(name = "aula_num",foreignKey = @ForeignKey(name="FK_AULA_EQUIPO"))
    private AulasEntity aula_num;
    @Basic
    @Column(name = "puesto", nullable = true)
    private Integer puesto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
    public Byte getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(Byte tipoEquipo) {
=======
    public Object getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipoEnum tipoEquipo) {
>>>>>>> ed18ab7994a95d3e9d80eb4f272c384927257e39
        this.tipoEquipo = tipoEquipo;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Byte getBaja() {
        return baja;
    }

    public void setBaja(Byte baja) {
        this.baja = baja;
    }


    public AulasEntity getAula_num() {
        return aula_num;
    }

    public void setAula_num(AulasEntity aula_num) {
        this.aula_num = aula_num;
    }

    public Integer getPuesto() {
        return puesto;
    }

    public void setPuesto(Integer puesto) {
        this.puesto = puesto;
    }
}
