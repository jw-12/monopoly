package jwrc.board;

public class Property extends BoardSpace {
	
	public String name;
	public int cost;
	public String owner;
	public String colour;
	public int noOfHouses;
	public boolean CanBuildHotel;
	
	public Property(String name, int cost, String colour, int index) {
		super(index, SpaceType.PROPERTY);
		this.name = name;
		this.cost = cost;
		this.colour = colour;
	}

}
