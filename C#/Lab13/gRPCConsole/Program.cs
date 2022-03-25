using gRPCServer;
using Grpc.Net.Client;
using GrpcServer.Protos;

// See https://aka.ms/new-console-template for more information
Console.WriteLine("Hello, World!");

var input = new HelloRequest { Name = "Jane Bond" };

var channel = GrpcChannel.ForAddress("http://localhost:5114");
var client = new Greeter.GreeterClient(channel);

var reply = await client.SayHelloAsync(input);

Console.WriteLine(reply.Message);


var sClient = new StudentRemote.StudentRemoteClient(channel);

/*
var sInput = new StudentLookupModel { StudentId = 3 };
var sReply = await sClient.GetStudentInfoAsync(sInput);
Console.WriteLine($"{sReply.StudentId}, {sReply.FirstName}  {sReply.LastName}, {sReply.School}");
*/

var student = new StudentModel
{
    StudentId = 100,
    FirstName = "Hang",
    LastName = "Liu",
    School = "BCIT"
};
var insertResult = sClient.InsertStudent(student);
Console.WriteLine($"{insertResult.Result}");

var sInput = new StudentLookupModel { StudentId = 100 };
var sReply = await sClient.GetStudentInfoAsync(sInput);
Console.WriteLine($"{sReply.StudentId}, {sReply.FirstName}  {sReply.LastName}, {sReply.School}");

student.School = "BCIT CST";
var updateResult = sClient.UpdateStudent(student);
Console.WriteLine($"{updateResult.Result}");

sReply = await sClient.GetStudentInfoAsync(sInput);
Console.WriteLine($"{sReply.StudentId}, {sReply.FirstName}  {sReply.LastName}, {sReply.School}");

var deleteReply = sClient.DeleteStudent(new StudentLookupModel{StudentId = 100});
Console.WriteLine($"{deleteReply.Result}");

// Console.ReadLine();
