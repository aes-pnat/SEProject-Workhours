class User{
    saveToken(token){
        localStorage.setItem("token", token);
    }
    getToken(){
        return localStorage.getItem("token");
    }
}

export default new User();