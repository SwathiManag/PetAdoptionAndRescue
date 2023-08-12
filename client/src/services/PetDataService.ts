import http from "@/http-common";

class PetDataService {
  getAll(): Promise<any> {
    return http.get("/pets");
  }

  get(id: any): Promise<any> {
    return http.get(`/pet/${id}`);
  }

  create(data: any): Promise<any> {
    return http.post("/pet", data);
  }

  update(id: any, data: any): Promise<any> {
    return http.put(`/pet/${id}`, data);
  }

  getbyFlagType(flag: any): Promise<any> {
    return http.get(`/pets/flag/${flag}`);
  }
  delete(id: any): Promise<any> {
    return http.delete(`/pet/${id}`);
  }

  updateReqStatus(id: any, updated_user_id: any, req_flag: any):Promise<any>{
    console.log("updated_user_id---"+updated_user_id);
    return http.put(`/pets/request/${id}/${updated_user_id}/${req_flag}`);
  }

  searchPetByName(searchParam: any): Promise<any> {
    return http.get(`/pets/search/${searchParam}`);
  }

  getbyUserId(user_id: any): Promise<any> {
    return http.get(`/pets/request/${user_id}`);
  }
}

export default new PetDataService();
