#include "Course.hpp"

#include <utility>

using namespace std;

Course::Course(string title, dayOfWeek day, unsigned int start_time,
               unsigned int finish_time) : title(std::move(title)), day(day),
                                           start_time(start_time),
                                           finish_time(finish_time) {
}

Course::Course(const Course &m) : title(m.title), day(m.day),
                                  start_time(m.start_time),
                                  finish_time(m.finish_time) {
}

void Course::swap(Course &m) {
    std::swap(title, m.title);
    std::swap(day, m.day);
    std::swap(start_time, m.start_time);
    std::swap(finish_time, m.finish_time);
}

Course &Course::operator=(Course m) {
    swap(m);
    return *this;
}

bool Course::operator==(const Course &m) const {
    if (title == m.title
        && day == m.day
        && start_time == m.start_time
        && finish_time == m.finish_time) {
        return true;
    }
    return false;
}

bool Course::operator<(const Course &m) const {
    if (day < m.day || (day == m.day && start_time < m.start_time)) {
        return true;
    }
    return false;
}

ostream &operator<<(ostream &os, const Course &m) {
    const vector<string> STR_DAYS{"M", "T", "W", "R", "F"};
    os << m.title << "\t"
       << STR_DAYS[m.day] << "\t"
       << m.start_time << "\t"
       << m.finish_time;
    return os;
}

Course::dayOfWeek Course::strToDay(const string &strDay) {
    const vector<string> STR_DAYS{"M", "T", "W", "R", "F"};
    for (size_t i = 0; i < STR_DAYS.size(); ++i) {
        if (STR_DAYS[i] == strDay) {
            return static_cast<dayOfWeek>(i);
        }
    }
    return UNK;
}

bool Course::isOverlapped(const Course &m) const {
    if (day == m.day &&
        start_time < m.finish_time &&
        finish_time > m.start_time) {
        return true;
    }
    return false;
}
