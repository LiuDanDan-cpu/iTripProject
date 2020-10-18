package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <b>返回前端-酒店各类评分VO</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public class ScoreCommentVO implements Serializable {
    private static final long serialVersionUID = 2718477944030679232L;
    private BigDecimal avgPositionScore;//点评查询页面酒店的位置得分
    private BigDecimal avgFacilitiesScore;//点评查询页面酒店的设施得分
    private BigDecimal avgServiceScore;//点评查询页面酒店的服务得分
    private BigDecimal avgHygieneScore;//点评查询页面酒店的卫生得分
    private BigDecimal avgScore;//点评查询页面酒店的总体得分

    public ScoreCommentVO() {
    }

    public ScoreCommentVO(BigDecimal avgPositionScore, BigDecimal avgFacilitiesScore, BigDecimal avgServiceScore, BigDecimal avgHygieneScore, BigDecimal avgScore) {
        this.avgPositionScore = avgPositionScore;
        this.avgFacilitiesScore = avgFacilitiesScore;
        this.avgServiceScore = avgServiceScore;
        this.avgHygieneScore = avgHygieneScore;
        this.avgScore = avgScore;
    }

    public BigDecimal getAvgPositionScore() {
        return avgPositionScore;
    }

    public void setAvgPositionScore(BigDecimal avgPositionScore) {
        this.avgPositionScore = avgPositionScore;
    }

    public BigDecimal getAvgFacilitiesScore() {
        return avgFacilitiesScore;
    }

    public void setAvgFacilitiesScore(BigDecimal avgFacilitiesScore) {
        this.avgFacilitiesScore = avgFacilitiesScore;
    }

    public BigDecimal getAvgServiceScore() {
        return avgServiceScore;
    }

    public void setAvgServiceScore(BigDecimal avgServiceScore) {
        this.avgServiceScore = avgServiceScore;
    }

    public BigDecimal getAvgHygieneScore() {
        return avgHygieneScore;
    }

    public void setAvgHygieneScore(BigDecimal avgHygieneScore) {
        this.avgHygieneScore = avgHygieneScore;
    }

    public BigDecimal getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(BigDecimal avgScore) {
        this.avgScore = avgScore;
    }
}
