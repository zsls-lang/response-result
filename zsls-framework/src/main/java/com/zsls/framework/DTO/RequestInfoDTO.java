package com.zsls.framework.DTO;

public class RequestInfoDTO {
      private String ip;
      private String url;
      private String httpMethod;
      private String classMethod;
      private Object requestParams;
      private Object result;
      private Long timeCost;

      public RequestInfoDTO() {
      }

      public String getIp() {
            return ip;
      }

      public void setIp(String ip) {
            this.ip = ip;
      }

      public String getUrl() {
            return url;
      }

      public void setUrl(String url) {
            this.url = url;
      }

      public String getHttpMethod() {
            return httpMethod;
      }

      public void setHttpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
      }

      public String getClassMethod() {
            return classMethod;
      }

      public void setClassMethod(String classMethod) {
            this.classMethod = classMethod;
      }

      public Object getRequestParams() {
            return requestParams;
      }

      public void setRequestParams(Object requestParams) {
            this.requestParams = requestParams;
      }

      public Object getResult() {
            return result;
      }

      public void setResult(Object result) {
            this.result = result;
      }

      public Long getTimeCost() {
            return timeCost;
      }

      public void setTimeCost(Long timeCost) {
            this.timeCost = timeCost;
      }
}