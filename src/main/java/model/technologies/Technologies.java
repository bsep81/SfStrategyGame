package model.technologies;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Technologies {

    private HullTechnology hullTechnology;
    private ShieldTechnology shieldTechnology;
    private AttackTechnology attackTechnology;
    private MiningTechnology miningTechnology;
    private AlloysTechnology alloysTechnology;
}
