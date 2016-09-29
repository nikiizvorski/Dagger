package com.androidchill.niki.dagger.model;

import java.util.List;

/* Created by NIKI on 9/28/2016. */

public class Response {

    /**
     * name : Grey Riding Yak
     * spellId : 127216
     * creatureId : 65017
     * itemId : 87788
     * qualityId : 4
     * icon : ability_mount_yakmountgrey
     * isGround : true
     * isFlying : false
     * isAquatic : true
     * isJumping : true
     */

    private List<MountsEntity> mounts;

    public void setMounts(List<MountsEntity> mounts) {
        this.mounts = mounts;
    }

    public List<MountsEntity> getMounts() {
        return mounts;
    }

    public static class MountsEntity {
        private String name;
        private int spellId;
        private int creatureId;
        private int itemId;
        private int qualityId;
        private String icon;
        private boolean isGround;
        private boolean isFlying;
        private boolean isAquatic;
        private boolean isJumping;

        public void setName(String name) {
            this.name = name;
        }

        public void setSpellId(int spellId) {
            this.spellId = spellId;
        }

        public void setCreatureId(int creatureId) {
            this.creatureId = creatureId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public void setQualityId(int qualityId) {
            this.qualityId = qualityId;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setIsGround(boolean isGround) {
            this.isGround = isGround;
        }

        public void setIsFlying(boolean isFlying) {
            this.isFlying = isFlying;
        }

        public void setIsAquatic(boolean isAquatic) {
            this.isAquatic = isAquatic;
        }

        public void setIsJumping(boolean isJumping) {
            this.isJumping = isJumping;
        }

        public String getName() {
            return name;
        }

        public int getSpellId() {
            return spellId;
        }

        public int getCreatureId() {
            return creatureId;
        }

        public int getItemId() {
            return itemId;
        }

        public int getQualityId() {
            return qualityId;
        }

        public String getIcon() {
            return "http://eu.media.blizzard.com/wow/icons/56/" + icon + ".jpg";
        }

        public String getMountPic() {
            return "http://media.blizzard.com/wow/renders/npcs/zoom/creature" + getCreatureId() + ".jpg";
        }

        public boolean isIsGround() {
            return isGround;
        }

        public boolean isIsFlying() {
            return isFlying;
        }

        public boolean isIsAquatic() {
            return isAquatic;
        }

        public boolean isIsJumping() {
            return isJumping;
        }
    }
}