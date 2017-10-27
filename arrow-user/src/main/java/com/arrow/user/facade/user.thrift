namespace java com.arrow.user.facade
exception ApplicationException{
    1: string message
}

service UserService{
 bool addUser(1: string data)
}