# Assignment 03 Design Document
Author: Hang Liu, Zichun Xu

## Summary
The timesheet management system will be divided into two parts: the back-end and the front-end:

1. The back-end part will be developed with ***JAX-RS***
2. The front-end part will be developed with ***React***

This document mainly focus on the back-end design.

## Code structure
The code has been divided into three parts:
1. The models related to the database tables
2. The EJB to manage the records in the database
3. The Restful APIs

## Requirements
1. The token system
   
    The user/client will get a token string after he has logged in. The subsequent request to the back-end must be sent with the token string, otherwise the backe-end will return an error info.

   

2. Timesheet management
   
   The user can search for the timesheet by selecting a date. If the timesheet doesn't exist, the back-end will create a new one with five empty rows for the user.

   The user can add/delete/view/modify a timesheet row.

   
   
3. Credential management
   
   a. The user can modify his own password.
   
   b. The admin can create/delete/modify users and their passwords

   c. The user cannot perform any operation except logging in if he has logged out

## APIs
All apis have a **code** in the response (even in the empty response). Code 0 indicates success, while code 1 indicates failure.

The following is an empty response without data:
```
{
    code: 0
}
```

The following is a response with data:
```
{
    code: 0,
    data: {
            ...data here...
    }
}
```

**hostname** depends on the domain name. For example: if the domain name is www.example.com, then the login api is http://www.example.come/user/login

### User Related APIs
1. Login
   
    |        |                                                              |
    | ------ | ------------------------------------------------------------ |
    | method | post                                                         |
    | path   | /api/user/login                                              |
    | body   | {<br/>    "userName": "admin",<br/>    "password": "admin"<br/>} |
    | return | {<br/>    "code": 0,<br/>    "data": {<br/>        "employee": {<br/>            "admin": 1,<br/>            "id": 1,<br/>            "username": "admin"<br/>        },<br/>        "token": "21232f297a57a5a743894a0e4a801fc3"<br/>    }<br/>} |

   

2. Validate token
   
    |        |                              |
    | ------ | ---------------------------- |
    | method | get                          |
    | path   | /api/user/info/token/{token} |
    | return | {<br/>    "code": 0<br/>}    |

   

3. Create a new user

    |        |                                                              |
    | ------ | ------------------------------------------------------------ |
    | method | post                                                         |
    | path   | /api/user/token/{token}                                      |
    | body   | {<br/>    "userName": "liuhang",<br/>    "password": "mypassword"<br/>} |
    | return | {<br/>    "code": 0<br/>}                                    |

    

4. Delete a user

    |        |                                 |
    | ------ | ------------------------------- |
    | method | delete                          |
    | path   | /api/user/token/{token}/id/{id} |
    | return | {<br/>    "code": 0<br/>}       |

    

5. Modify a user

    |            |                                 |
    | ---------- | ------------------------------- |
    | method     | put                             |
    | path       | /api/user/token/{token}/id/{id} |
    | parameters | userName, password, name        |
    | return     | {<br/>    "code": 0<br/>}       |

    

6. Modify password

    |        |                                             |
    | ------ | ------------------------------------------- |
    | method | put                                         |
    | path   | /api/user/token/{token}/password/{password} |
    | return | {<br/>    "code": 0<br/>}                   |

    



### Timesheet related APIs

1. Get timesheet   
    |        |                                                              |
    | ------ | ------------------------------------------------------------ |
    | method | get                                                          |
    | path   | /api/timesheet/token/{token}/date/{date}                     |
    | return | {<br/>    "code": 0,<br/>    "data": [<br/>        {<br/>            "deciSum": 0,<br/>            "decihours": [0, 0, 0, 0, 0, 0, 0],<br/>            "notes": "",<br/>            "packedHours": 0,<br/>            "projectId": 0,<br/>            "sum": 0,<br/>            "workPackageId": "",<br/>            "hours": [0, 0, 0, 0, 0, 0, 0],<br/>             "id": 1<br/>        }<br />    ]<br/>} |

    
    
2. Add a new row 
   
    |        |                                          |
    | ------ | ---------------------------------------- |
    | method | put                                      |
    | path   | /api/timesheet/token/{token}/date/{date} |
    | return | {<br/>    "code": 0<br/>}                |
    
    
    
3. Delete a row
   
    |        |                                      |
    | ------ | ------------------------------------ |
    | method | delete                               |
    | path   | /api/timesheet/token/{token}/id/{id} |
    | return | {<br/>    "code": 0<br/>}            |
    
    
    
4. Update a new row 
   
    |            |                                       |
    | ---------- | ------------------------------------- |
    | method     | post                                  |
    | path       | /api/timesheet/token/{token}          |
    | parameters | row id, project id, workpackage, etc. |
    | return     | {<br/>    "code": 0<br/>}             |