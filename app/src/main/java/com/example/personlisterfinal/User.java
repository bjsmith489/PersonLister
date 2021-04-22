package com.example.personlisterfinal;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int id;
    private int image;
    private Address address;
    private String name;
    private String username;
    private String email;

    protected User(Parcel in) {
        id = in.readInt();
        image = in.readInt();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setImage(int image){ this.image = image;}
    public int getImage(){ return this.image; }
    public Address getAddress() { return address; }
    public String getName() { return name; }
    public String getUserName() { return username; }
    public String getEmail() { return email; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeParcelable(address, flags);
    }

    public static class Address implements Parcelable{
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        protected Address(Parcel in) {
            street = in.readString();
            suite = in.readString();
            city = in.readString();
            zipcode = in.readString();
            geo = in.readParcelable(Geo.class.getClassLoader());
        }

        public static final Creator<Address> CREATOR = new Creator<Address>() {
            @Override
            public Address createFromParcel(Parcel in) {
                return new Address(in);
            }

            @Override
            public Address[] newArray(int size) {
                return new Address[size];
            }
        };

        public String getStreet() { return street; }
        public String getSuite() { return suite;  }
        public String getCity() {return city;}
        public String getZipcode() { return zipcode;}
        public Geo getGeo() { return geo;}

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", suite='" + suite + '\'' +
                    ", city='" + city + '\'' +
                    ", zipcode='" + zipcode + '\'' +
                    ", geo=" + this.geo.toString() +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(street);
            dest.writeString(suite);
            dest.writeString(city);
            dest.writeString(zipcode);
            dest.writeParcelable(geo, flags);
        }
    }

    public static class Geo implements Parcelable{
        private String lat;
        private String lng;

        protected Geo(Parcel in) {
            lat = in.readString();
            lng = in.readString();
        }

        public static final Creator<Geo> CREATOR = new Creator<Geo>() {
            @Override
            public Geo createFromParcel(Parcel in) {
                return new Geo(in);
            }

            @Override
            public Geo[] newArray(int size) {
                return new Geo[size];
            }
        };

        public String getLat() { return lat; }
        public String getLng() { return lng; }

        @Override
        public String toString() {
            return "Geo{" +
                    "lat='" + lat + '\'' +
                    ", lng='" + lng + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(lat);
            dest.writeString(lng);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "address=" + this.address.toString() +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
