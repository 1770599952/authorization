package com.ycu.dto;

import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ycu.bean.Ad;

@JsonInclude(Include.NON_NULL)
public class AdDto extends Ad{
	
    private String img;
    
    private MultipartFile imgFile;

    
    public AdDto() {
		super();
	}

	public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

	@Override
	public String toString() {
		return super.toString();
	}

    

    
}
