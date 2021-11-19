package com.sarilhos.app.todo;

import lombok.*;


@Data
@AllArgsConstructor
@EqualsAndHashCode()
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TodoRestDto {

    private String id;
    private String title;
    private String content;


    @Override
    public String toString() {
        return "RestDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
