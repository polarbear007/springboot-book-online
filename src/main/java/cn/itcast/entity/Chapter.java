package cn.itcast.entity;

import java.util.Date;

public class Chapter {
    private Integer chapterId;

    private Integer chapterNum;

    private String chapterTitle;

    private Integer chapterLength;

    private Date lastUpdateTime;

    private Integer bookId;

    private String chapterContent;
    
    private BookInfo bookInfo;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle == null ? null : chapterTitle.trim();
    }

    public Integer getChapterLength() {
        return chapterLength;
    }

    public void setChapterLength(Integer chapterLength) {
        this.chapterLength = chapterLength;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(String chapterContent) {
        this.chapterContent = chapterContent == null ? null : chapterContent.trim();
    }

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	
	@Override
	public String toString() {
		return "Chapter [chapterId=" + chapterId + ", chapterNum=" + chapterNum + ", chapterTitle=" + chapterTitle
				+ ", chapterLength=" + chapterLength + ", lastUpdateTime=" + lastUpdateTime + ", bookId=" + bookId
				+ ", chapterContent=" + chapterContent + ", bookInfo=" + bookInfo + "]";
	}
}