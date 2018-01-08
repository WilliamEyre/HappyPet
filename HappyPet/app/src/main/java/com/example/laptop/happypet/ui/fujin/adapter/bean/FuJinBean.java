package com.example.laptop.happypet.ui.fujin.adapter.bean;

import java.util.List;

/**
 * Created by Laptop on 2018/1/6.
 */
public class FuJinBean {
    /**
     * ret : true
     * desc : [{"score":3.5,"address":"四平市 北京市昌平区沙河","coordX":"40.134235","userImage":"http://q.qlogo.cn/qqapp/100371282/B368CC7246CC4A360C4305F64FE9173A/40","coordY":"116.280098","distance":9,"price":10,"orderCount":37,"usersId":"d80488022f1e4278a3149f54beeac02a","family":"李丽丽了"},{"score":3.6667,"address":"北京市昌平区沙河沙阳路18号北京科技职业学院","coordX":"40.11765","userImage":"http://q.qlogo.cn/qqapp/100371282/290BB8E0BEC8DF5989060A6947C3E75D/40","coordY":"116.250639","distance":10,"price":20,"orderCount":84,"usersId":"536e2c7b99204bbb81ad8fa7e6b2860f","family":"小街爆的家"},{"score":0,"address":"福建省.福州市.枞阳县","coordX":"40.0493","userImage":"http://q.qlogo.cn/qqapp/100371282/B368CC7246CC4A360C4305F64FE9173A/40","coordY":"116.296482","distance":18.318,"price":30,"orderCount":23,"usersId":"6e710fd188b94d12bf12a6509ff3fe1f","family":"寄养998"},{"score":0,"address":"北京市 西城区","coordX":"39.912289","userImage":"http://q.qlogo.cn/qqapp/100371282/290BB8E0BEC8DF5989060A6947C3E75D/40","coordY":"116.365868","distance":33.637,"price":10,"orderCount":1,"usersId":"43e2a6c8829245d488f90197e3c84b08","family":"昵称"},{"score":3.2222,"address":"北京昌平","coordX":"39.906898","userImage":"http://q.qlogo.cn/qqapp/100371282/B368CC7246CC4A360C4305F64FE9173A/40","coordY":"116.397238","distance":35.496,"price":30,"orderCount":73,"usersId":"f30e56db51d7ce93b3b58dbb16aea142","family":"张三三"},{"score":0,"address":"北京市","coordX":"39.90403","userImage":"http://q.qlogo.cn/qqapp/1105285855/862B29312F8D30591BEAC254DAFA551E/40","coordY":"116.407526","distance":36.242,"price":10,"orderCount":1,"usersId":"893cb45fa9ea4ffb8c9b28656f41a146","family":"zachay"},{"score":0,"address":"北京市","coordX":"39.90403","userImage":"http://q.qlogo.cn/qqapp/100371282/B368CC7246CC4A360C4305F64FE9173A/40","coordY":"116.407526","distance":36.242,"price":10,"orderCount":4,"usersId":"fca3070783a94babaee37c52a52bb14a","family":"咿呀咿呀哟"},{"score":0,"address":"北京市城市\t北京市 海淀区\t城市\t北京市 昌平区","coordX":"39.92147","userImage":"http://q.qlogo.cn/qqapp/1105285855/862B29312F8D30591BEAC254DAFA551E/40","coordY":"116.443108","distance":36.679,"price":30,"orderCount":0,"usersId":"a2ac592b1bdc4316a61054db23896f9a","family":"好放假了是的回复"},{"score":0,"address":"吕梁市汾阳市东关村牛王堂","coordX":"36.856329","userImage":"http://q.qlogo.cn/qqapp/100371282/B368CC7246CC4A360C4305F64FE9173A/40","coordY":"114.494758","distance":378.488,"price":40,"orderCount":2,"usersId":"63344fce512f449a988b1f330ee0e8db","family":"家家"},{"score":0,"address":"北京市九寨沟","coordX":"36.856329","userImage":"http://q.qlogo.cn/qqapp/100371282/290BB8E0BEC8DF5989060A6947C3E75D/40","coordY":"114.494758","distance":378.488,"orderCount":0,"usersId":"0e6a26bb351c4a818512c72d4b6bd7e5","family":"家家"}]
     */

    private boolean ret;
    private List<DescBean> desc;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public List<DescBean> getDesc() {
        return desc;
    }

    public void setDesc(List<DescBean> desc) {
        this.desc = desc;
    }

    public static class DescBean {
        /**
         * score : 3.5
         * address : 四平市 北京市昌平区沙河
         * coordX : 40.134235
         * userImage : http://q.qlogo.cn/qqapp/100371282/B368CC7246CC4A360C4305F64FE9173A/40
         * coordY : 116.280098
         * distance : 9
         * price : 10
         * orderCount : 37
         * usersId : d80488022f1e4278a3149f54beeac02a
         * family : 李丽丽了
         */

        private double score;
        private String address;
        private String coordX;
        private String userImage;
        private String coordY;
        private int distance;
        private int price;
        private int orderCount;
        private String usersId;
        private String family;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCoordX() {
            return coordX;
        }

        public void setCoordX(String coordX) {
            this.coordX = coordX;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getCoordY() {
            return coordY;
        }

        public void setCoordY(String coordY) {
            this.coordY = coordY;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public String getUsersId() {
            return usersId;
        }

        public void setUsersId(String usersId) {
            this.usersId = usersId;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        @Override
        public String toString() {
            return "DescBean{" +
                    "score=" + score +
                    ", address='" + address + '\'' +
                    ", coordX='" + coordX + '\'' +
                    ", userImage='" + userImage + '\'' +
                    ", coordY='" + coordY + '\'' +
                    ", distance=" + distance +
                    ", price=" + price +
                    ", orderCount=" + orderCount +
                    ", usersId='" + usersId + '\'' +
                    ", family='" + family + '\'' +
                    '}';
        }
    }
}
