package org.md.deidentifier;

import java.util.ArrayList;

import weka.core.Instance;
import weka.core.Instances;

public class Dictionary {
	public ArrayList<Key> valueKeys;
	public ArrayList<Key> nameKeys;
	
	public Dictionary()
	{
		
	}
	
	public void createDictionary(Instances data)
	{
		// encode attribute names
		valueKeys= new ArrayList<Key>();
		nameKeys= new ArrayList<Key>();
		Key key;
		Instances newData = new Instances(null, null, 0);
		for (int i = 0; i < data.numAttributes(); i++)
		{
			if (data.attribute(i).isNominal())
			{
				key = new Key(i,data.attribute(i).name(),"attribute"+i);
				
				nameKeys.add(key);
				data.renameAttribute(data.attribute(i), key.newValue);
			}
		}
		
		// encode attribute values
		int[] indexes = new int[data.numAttributes()];
		
		Instance ins;
		for (int i = 0; i < data.numInstances(); i++)
		{
			System.out.println("patata" + i);
			ins = data.instance(i);
			for (int j = 0; j < data.numAttributes(); j++)
			{
				if (data.attribute(j).isNominal())
				{
					String value = hasAppeared(j, ins.stringValue(j));
					if (value!=null)
					{
						data.attribute(j).addStringValue(value);
						ins.setValue(j, value);
					}
					else
					{
						indexes[j]=indexes[j]+1;
						value = createKey(j, ins.stringValue(j), indexes[j]);
						System.out.println(data.
						System.out.println(data.attribute(j).value(8));
						ins.setValue(j, value);
						
					}
				}
			}
				
		}
	}
	
	public String hasAppeared(int index, String value)
	{
		for (int i = 0; i < valueKeys.size(); i++)
		{
			if(valueKeys.get(index).originalValue.compareTo(value)==0)
			{	
				return valueKeys.get(index).newValue;
			}
		}
		return null;
	}
	
	public String createKey(int index, String value, int code)
	{
		Key key = new Key(index, value, value + String.valueOf(code));
		valueKeys.add(key);
		return key.newValue;
	}

}
