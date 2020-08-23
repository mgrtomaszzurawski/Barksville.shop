package pl.barksville.barksville.spring.model.entities.base;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Setter
@Getter
@MappedSuperclass
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
/*
    Nie generujemy metody equals i hashCode z poziomu adnotacji lomboka,
    aby było wyraźnie widać, że mają zostać oparte tylko na polu id
 */
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        createdOn = ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).toLocalDateTime();
        updatedOn = ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).toLocalDateTime();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


}
