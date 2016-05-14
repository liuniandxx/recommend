package com.weego.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author ln
 */
public class Timezone {
    private Integer dstOffset;

    private Integer rawOffset;

    public Integer getDstOffset() {
        return dstOffset;
    }

    public void setDstOffset(Integer dstOffset) {
        this.dstOffset = dstOffset;
    }

    public Integer getRawOffset() {
        return rawOffset;
    }

    public void setRawOffset(Integer rawOffset) {
        this.rawOffset = rawOffset;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                    .append("dstOffSet", dstOffset)
                    .append("rawOffSet", rawOffset)
                    .toString();
    }
}
