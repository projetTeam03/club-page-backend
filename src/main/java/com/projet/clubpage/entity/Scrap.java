package com.projet.clubpage.entity;


import com.projet.clubpage.entity.embeddedId.ScrapId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="user_scrap")
@NoArgsConstructor
public class Scrap {

    @EmbeddedId
    private ScrapId scrapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recruitId")
    @JoinColumn(name = "recruit_idx")
    private Recruit recruit;

}
