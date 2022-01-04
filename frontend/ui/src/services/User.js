
class User{
    saveToken(token){
        localStorage.setItem("user", token);
    }
    getToken(){
        return localStorage.getItem("user");
    }
    removeToken(){
        localStorage.removeItem("user");
    }
    saveRole(role){
        localStorage.setItem("role",role);
    }
    getRole(){
        let role = localStorage.getItem("role");
        if(role){
            return role;
        }else{
            return ""
        }
    }
}

export default new User();