package pl.barksville.barksville.spring.model.entities.reports;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.barksville.barksville.spring.model.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "week_reports")
@Getter
@Setter
@ToString
public class WeekReport extends BaseEntity {
    //todo
}
