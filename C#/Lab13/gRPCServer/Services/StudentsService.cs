using Grpc.Core;
using gRPCServer.Data;
using gRPCServer.Models;
using GrpcServer.Protos;

namespace gRPCServer.Services
{
    public class StudentsService : StudentRemote.StudentRemoteBase
    {
        private readonly ILogger<StudentsService> _logger;
        private readonly SchoolDbContext _context;

        public StudentsService(ILogger<StudentsService> logger, SchoolDbContext context)
        {
            _logger = logger;
            _context = context;
        }

        public override Task<StudentModel> GetStudentInfo(StudentLookupModel request, ServerCallContext context)
        {
            StudentModel output = new StudentModel();

            var c = _context.Students.Find(request.StudentId);

            _logger.LogInformation("Sending Student response");

            if (c != null)
            {
                output.StudentId = c.StudentId;
                output.FirstName = c.FirstName;
                output.LastName = c.LastName;
                output.School = c.School;
            }

            return Task.FromResult(output);
        }

        public override Task<Reply> InsertStudent(StudentModel student, ServerCallContext context)
        {
            Reply r = new Reply
            {
                Result = "Insert Success",
                IsOk = true
            };

            Student s = new Student
            {
                StudentId = student.StudentId,
                FirstName = student.FirstName,
                LastName = student.LastName,
                School = student.School
            };

            _context.Students.Add(s);
            _context.SaveChanges();

            return Task.FromResult(r);
        }

        public override Task<Reply> UpdateStudent(StudentModel student, ServerCallContext context)
        {
            Reply r = new Reply
            {
                Result = "Update Success",
                IsOk = true
            };

            var c = _context.Students.Find(student.StudentId);

            if (c == null)
            {
                r.Result = "Can't find!";
                r.IsOk = false;
                return Task.FromResult(r);
            }

            c.StudentId = student.StudentId;
            c.FirstName = student.FirstName;
            c.LastName = student.LastName;
            c.School = student.School;

            _context.Students.Update(c);
            _context.SaveChanges();

            return Task.FromResult(r);
        }

        public override Task<Reply> DeleteStudent(StudentLookupModel student, ServerCallContext context)
        {
            Reply r = new Reply
            {
                Result = "Delete Success",
                IsOk = true
            };

            var c = _context.Students.Find(student.StudentId);

            if (c == null)
            {
                r.Result = "Can't find!";
                r.IsOk = false;
                return Task.FromResult(r);
            }

            _context.Students.Remove(c);
            _context.SaveChanges();

            return Task.FromResult(r);
        }

        public override Task<StudentList> RetrieveAllStudents(Empty e, ServerCallContext context)
        {
            StudentList list = new StudentList();

            var c = _context.Students.Select(_ => _);

            foreach (var s in c)
            {
                list.Items.Add(
                    new StudentModel
                    {
                        StudentId = s.StudentId,
                        FirstName = s.FirstName,
                        LastName = s.LastName,
                        School = s.School,
                    }
                );
            }

            return Task.FromResult(list);
        }
    }

}