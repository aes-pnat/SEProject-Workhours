
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
}

export default new User();