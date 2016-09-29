package com.androidchill.niki.dagger.model;

import java.util.List;

/* Created by NIKI on 9/28/2016. */

public class ResponsePets {

    /**
     * canBattle : true
     * creatureId : 17254
     * name : Ash'ana
     * family : beast
     * icon : ability_mount_whitetiger
     * qualityId : 4
     * stats : {"speciesId":1927,"breedId":3,"petQualityId":1,"level":1,"health":147,"power":10,"speed":9}
     * strongAgainst : ["critter"]
     * typeId : 7
     * weakAgainst : ["flying"]
     */

    private List<PetsBean> pets;

    public List<PetsBean> getPets() {
        return pets;
    }

    public void setPets(List<PetsBean> pets) {
        this.pets = pets;
    }

    public static class PetsBean {
        private boolean canBattle;
        private int creatureId;
        private String name;
        private String family;
        private String icon;
        private int qualityId;
        /**
         * speciesId : 1927
         * breedId : 3
         * petQualityId : 1
         * level : 1
         * health : 147
         * power : 10
         * speed : 9
         */

        private StatsBean stats;
        private int typeId;
        private List<String> strongAgainst;
        private List<String> weakAgainst;

        public boolean isCanBattle() {
            return canBattle;
        }

        public void setCanBattle(boolean canBattle) {
            this.canBattle = canBattle;
        }

        public int getCreatureId() {
            return creatureId;
        }

        public void setCreatureId(int creatureId) {
            this.creatureId = creatureId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public String getIcon() {
            return "http://eu.media.blizzard.com/wow/icons/56/" + icon + ".jpg";
        }

        public String getPetPic() {
            return "http://media.blizzard.com/wow/renders/npcs/zoom/creature" + getCreatureId() + ".jpg";
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getQualityId() {
            return qualityId;
        }

        public void setQualityId(int qualityId) {
            this.qualityId = qualityId;
        }

        public StatsBean getStats() {
            return stats;
        }

        public void setStats(StatsBean stats) {
            this.stats = stats;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public List<String> getStrongAgainst() {
            return strongAgainst;
        }

        public void setStrongAgainst(List<String> strongAgainst) {
            this.strongAgainst = strongAgainst;
        }

        public List<String> getWeakAgainst() {
            return weakAgainst;
        }

        public void setWeakAgainst(List<String> weakAgainst) {
            this.weakAgainst = weakAgainst;
        }

        public static class StatsBean {
            private int speciesId;
            private int breedId;
            private int petQualityId;
            private int level;
            private int health;
            private int power;
            private int speed;

            public int getSpeciesId() {
                return speciesId;
            }

            public void setSpeciesId(int speciesId) {
                this.speciesId = speciesId;
            }

            public int getBreedId() {
                return breedId;
            }

            public void setBreedId(int breedId) {
                this.breedId = breedId;
            }

            public int getPetQualityId() {
                return petQualityId;
            }

            public void setPetQualityId(int petQualityId) {
                this.petQualityId = petQualityId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public int getHealth() {
                return health;
            }

            public void setHealth(int health) {
                this.health = health;
            }

            public int getPower() {
                return power;
            }

            public void setPower(int power) {
                this.power = power;
            }

            public int getSpeed() {
                return speed;
            }

            public void setSpeed(int speed) {
                this.speed = speed;
            }
        }
    }
}
