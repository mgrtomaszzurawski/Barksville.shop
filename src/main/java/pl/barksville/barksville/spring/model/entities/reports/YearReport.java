package pl.barksville.barksville.spring.model.entities.reports;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "year_reports")
@Getter
@Setter
@ToString
public class YearReport extends BaseEntity {
    //todo
}
