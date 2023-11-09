import datetime

class StudyTracker:
    def __init__(self):
        self.study_sessions = []

    def start_study_session(self):
        start_time = datetime.datetime.now()
        return start_time

    def end_study_session(self, start_time, subject, duration_minutes):
        end_time = datetime.datetime.now()
        study_session = {
            "start_time": start_time,
            "end_time": end_time,
            "subject": subject,
            "duration_minutes": duration_minutes,
        }
        self.study_sessions.append(study_session)

    def display_study_sessions(self):
        for session in self.study_sessions:
            print(f"Subject: {session['subject']}")
            print(f"Start Time: {session['start_time']}")
            print(f"End Time: {session['end_time']}")
            print(f"Duration (minutes): {session['duration_minutes']}")
            print("\n")

def main():
    tracker = StudyTracker()

    while True:
        print("Options:")
        print("1. Start a study session")
        print("2. End the current study session")
        print("3. Display study sessions")
        print("4. Exit")
        
        choice = input("Enter your choice: ")
        
        if choice == "1":
            subject = input("Enter the subject: ")
            start_time = tracker.start_study_session()
            print(f"Started studying {subject} at {start_time}")
        elif choice == "2":
            duration_minutes = int(input("Enter the duration of the study session (in minutes): "))
            tracker.end_study_session(start_time, subject, duration_minutes)
            print(f"Ended studying {subject} after {duration_minutes} minutes.")
        elif choice == "3":
            tracker.display_study_sessions()
        elif choice == "4":
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
