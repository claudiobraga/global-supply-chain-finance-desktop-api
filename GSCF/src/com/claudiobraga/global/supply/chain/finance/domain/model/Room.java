package com.claudiobraga.global.supply.chain.finance.domain.model;

public class Room {
	
	private Integer length;
	private Integer width;
	private Integer height;
	public Integer dimensions;
	private int position;
	private static Integer count = 0;
	
	public Room() {
		
	}
	
	public Room(Integer length, Integer width, Integer height ) {
		
		this.length = length;
		this.width = width;
		this.height = height;
		++count;
		position += count;
	}
	
    public Integer surfaceAreaRoomCalculation() {
        dimensions = (length * 2 * width) + (width * 2 * height) + (height * 2 * length);        
        return dimensions;
    }
    
    public Integer smallestSide() {
    	
    	int min = 0;
    	int a = (length * width);
    	int b = (width * height);
    	int c = (height * length);
    	
    	if (a < b) {
			min = a;
		}else {
			min = b;
			if (min > c) {
				min = c;
			}
		}
    	return min;
    }
    
    @Override
    public String toString() {
    	return " Room["+position+"]{" + "length=" + length + ", width=" + width + ", height=" + height + 
    			"}"+ " Dimensions = "+surfaceAreaRoomCalculation()+" + "+smallestSide()+" = "
    			+(surfaceAreaRoomCalculation()+smallestSide());
    }

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getDimensions() {
		return dimensions;
	}

	public void setDimensions(Integer dimensions) {
		this.dimensions = dimensions;
	}
	
	public int getTotal() {
		return surfaceAreaRoomCalculation()+smallestSide();
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void copy(Room room) {
		this.length = room.getLength();
		this.width = room.getWidth();
		this.height = room.getHeight();
		this.position = room.getPosition();
	}
}