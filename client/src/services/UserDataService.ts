import http from "@/http-common";

class UserDataService {

  getAll(): Promise<any> {
    return http.get(`/users`);
  }

  get(id: any): Promise<any> {
    return http.get(`/user/${id}`);
  }

  loginUser(data: any): Promise<any> {
    return http.post(`/auth/signin`,data);
  }

  create(data: any): Promise<any> {
    return http.post(`/auth/signup`, data);
  }

  update(id: any, data: any): Promise<any> {
    return http.put(`/user/${id}`, data);
  }

  delete(id: any): Promise<any> {
    return http.delete(`/user/${id}`);
  }

  validateUser(userName:any, securityQues:any):Promise<any> {
    return http.get(`/user/validate/${userName}/${securityQues}`);
  }

  resetPwd(data: any): Promise<any> {
    return http.put(`/user/resetPassword`, data);
  }
}

export default new UserDataService();
