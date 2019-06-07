package cn.itcast.entity;

import java.util.Date;

public class BookInfo {
    private Integer bookId;

    private String bookName;

    private String bookIntroduce;

    private Integer bookLength;

    private Date lastUpdateTime;

    private Integer bookState;

    private Integer isFree;

    private String bookCoverPath;

    private Integer categoryId;

    private Integer authorId;
    
    private BookCategory category;
    
    private Author author;

    public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookIntroduce() {
        return bookIntroduce;
    }

    public void setBookIntroduce(String bookIntroduce) {
        this.bookIntroduce = bookIntroduce == null ? null : bookIntroduce.trim();
    }

    public Integer getBookLength() {
        return bookLength;
    }

    public void setBookLength(Integer bookLength) {
        this.bookLength = bookLength;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getBookState() {
        return bookState;
    }

    public void setBookState(Integer bookState) {
        this.bookState = bookState;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getBookCoverPath() {
        return bookCoverPath;
    }

    public void setBookCoverPath(String bookCoverPath) {
        this.bookCoverPath = bookCoverPath == null ? null : bookCoverPath.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

	@Override
	public String toString() {
		return "BookInfo [bookId=" + bookId + ", bookName=" + bookName + ", bookIntroduce=" + bookIntroduce
				+ ", bookLength=" + bookLength + ", lastUpdateTime=" + lastUpdateTime + ", bookState=" + bookState
				+ ", isFree=" + isFree + ", bookCoverPath=" + bookCoverPath + ", categoryId=" + categoryId
				+ ", authorId=" + authorId + ", category=" + category + ", author=" + author + "]";
	}
    
}