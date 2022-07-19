package com.example.demo;

import java.util.Iterator;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoCrud {
	public static MongoClient mongo;
	public static MongoDatabase database;
	public static MongoCollection<Document> collection;
	public void insert(int id, String name, int salary)
	{
		try
        {
			mongo = new MongoClient( "localhost" , 27017 );
			database = mongo.getDatabase("Bhavesh");
			collection = database.getCollection("employee");
			Document document = new Document("id", id)
			.append("name", name)
			.append("salary", salary);
			collection.insertOne(document);
			mongo.close();
        }
        catch(Exception e) {System.out.print(e);}
	}
	
    public void getAllEmployees()
    {
		try
		{
			mongo = new MongoClient( "localhost" , 27017 );
			database = mongo.getDatabase("Bhavesh");
			collection = database.getCollection("employee");
			FindIterable<Document> iterDoc = collection.find();
		    Iterator<Document> it = iterDoc.iterator();
		    while (it.hasNext()) 
		    {
		         System.out.println(it.next());
		    }
		    mongo.close();
		}catch(Exception e){e.printStackTrace();}
	}
        
    public void getEmployeeById(int id)
    {
        try
        {
        	mongo = new MongoClient( "localhost" , 27017 );
			database = mongo.getDatabase("Bhavesh");
			collection = database.getCollection("employee");
        	Bson filter = new Document("id", id);
    		FindIterable<Document> iterDoc = collection.find(filter);
    	    Iterator<Document> it = iterDoc.iterator();
    	    while (it.hasNext()) {
    	         System.out.println(it.next());
    	    }
    	    mongo.close();
		}catch(Exception ex)
        {ex.printStackTrace();}
	} 

    public void update(int empid, int id, String name, int salary)
    {
		try
		{
			mongo = new MongoClient( "localhost" , 27017 );
			database = mongo.getDatabase("Bhavesh");
			collection = database.getCollection("employee");
			Bson filter = new Document("id", empid);
		    Bson newValue = new Document("id", id).append("name", name).append("salary", salary);
		    Bson updateOperationDocument = new Document("$set", newValue);
		    collection.updateMany(filter, updateOperationDocument);
		    mongo.close();
		}catch(Exception ex){ex.printStackTrace();}
	}

    public void delete(int id)
    {
		try
		{
			mongo = new MongoClient( "localhost" , 27017 );
			database = mongo.getDatabase("Bhavesh");
			collection = database.getCollection("employee");
			Document document = new Document("id", id);
					collection.deleteOne(document);
					mongo.close();
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		MongoCrud obj=new MongoCrud();
		while (true) {
		System.out.println("Select Following Option \n1 for Add Employee\n2 for List of Employee\n3 for update Employeee Details\n4 for Delete Employee Record");
		int input = sc.nextInt();
		switch (input) 
		{
		  case 1:
			  System.out.println("Enter Employee Id, Name And Salary");
			  int id=sc.nextInt();
			  String name=sc.next();
			  int salary=sc.nextInt();
			obj.insert(id, name, salary);
		    break;
		  case 2:
			  System.out.println("\nEmployee List");
		    obj.getAllEmployees();
		    break;
		  case 3:
		    System.out.println("\nUpdate Employee");
		    System.out.println("Enter Employee Id");
		    int empid=sc.nextInt();
		    obj.getEmployeeById(empid);
		    System.out.println("Enter Employee Details for Update id, Name And Salary");
		    id=sc.nextInt();
			name=sc.next();
			salary=sc.nextInt();
		    obj.update(empid, id, name, salary);
		    
		    break;
		  case 4:
		    System.out.println("\nDelete Employee \n Enter A Employee Id");
		    id=sc.nextInt();
		    obj.getEmployeeById(id);
		    obj.delete(id);
		    
		    break;
		}
		}
	}
}
