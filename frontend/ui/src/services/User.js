
class User{
    saveToken(token){
        localStorage.setItem("token", token);
    }
    getToken(){
        let token = localStorage.getItem("token");
        return token != null ? token : "";
    }
    removeToken(){
        localStorage.removeItem("token");
    }
    saveRole(role){
        localStorage.setItem("role",role);
    }
    getRole(){
        let role = localStorage.getItem("role");
        return role != null ? role : "";
    }
    removeRole(){
        localStorage.removeItem("role");
    }
}

export default new User();