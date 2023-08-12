import http from "@/http-common";

class EventDataService {
    getAll(): Promise<any> {
        return http.get("/events");
    }

    get(id: any): Promise<any> {
        return http.get(`/events/${id}`);
    }

    create(data: any): Promise<any> {
        return http.post("/events", data);
    }

    update(id: any, data: any): Promise<any> {
        return http.put(`/event/${id}`, data);
    }


    delete(id: any): Promise<any> {
        return http.delete(`/event/${id}`);
    }

    participateEvent(data: any):Promise<any>{
        return http.post(`/events/eventParticipant`, data);
    }

}

export default new EventDataService();