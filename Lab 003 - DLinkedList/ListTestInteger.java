public class ListTestInteger extends ListTest<Integer>{
	static Integer i =0;
	@Override
	public Integer getParameterInstance() {
		//TODO add your implementation
		i++;
		return i;
	}

}
