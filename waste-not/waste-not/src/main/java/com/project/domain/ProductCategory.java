package com.project.domain;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;


public enum ProductCategory {
	
	FOOD, FURNITURE, STATIONERY, CLOTHES, TOYS, CHILDREN, BABIES, TOOLS, ELECTRONICS, MISCELLANEOUS;
	
	public String getInfoAndImage(){
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "julie333",
				  "api_key", "394711512782567",
				  "api_secret", "ntD2OfhS4Bz1V_dopDnX_gn85xc"));
		
		switch (this) {

		case FOOD:
		String imageUrl = cloudinary.url()
		.transformation(new Transformation().width(100).height(150).crop("fill")).generate("sample.jpg");
		return imageUrl;
		
		case FURNITURE:
			return "Description";
		case STATIONERY:
			return "Description";
		case CLOTHES:
			return "Description";
		case TOYS:
			return "Description";
		case CHILDREN:
			return "Description";
		case BABIES:
			return "Description";
		case TOOLS:
			return "Description";
		case ELECTRONICS:
			return "Description";
			default : return "Invalid Category";
		}
		
	}

}


//p.getProductCategory().getInfo()
